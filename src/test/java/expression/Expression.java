/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */
package expression;

import expression.element.ExpressionElement;
import expression.element.Operand;
import expression.element.operator.Operator;
import expression.element.operator.impl.Divide;
import expression.element.operator.impl.LeftBracket;
import expression.element.operator.impl.Multiply;
import expression.element.operator.impl.Plus;
import expression.element.operator.impl.RightBracket;
import expression.element.operator.impl.Subtract;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Stack;

/**
 * <p>不支持带负数和小数的表达式</p>
 *
 * @author @leiguowei@meituan.com
 * @version $Id: Expression.java, v 0.1 2018-10-18 10:59 PM @leiguowei $$
 */
public class Expression {
    /**
     * 表达式的原始字符串
     */
    private String origin;
    /**
     * 表达式的头元素，可从这里遍历整个表达式
     */
    private ExpressionElement head;
    /**
     * 表达式的尾元素，用于解析过程中向表达式链表上添加节点
     */
    private ExpressionElement tail;
    /**
     * 该表达式所有的操作数
     */
    private List<Operand> operandList = new ArrayList<>();
    /**
     * 该表达式所有的计算符号
     */
    private List<Operator> operatorList = new ArrayList<>();
    /**
     * 该表达式的计算结果
     */
    private double computeResult;

    public Expression(String origin) {
        this.origin = origin.trim();
        try {
            computeResult = calculate(this.origin);
        } catch (Exception e) {
            //通过能够计算出正确的结果来判断该表达式是否是合法的
            e.printStackTrace();
            throw new IllegalExpression();
        }
    }

    /**
     * 解析表达式的元素，比如操作数，运算符
     *
     * @param expression 表达式字符串
     * @param startIndex 解析的起始位置
     * @return 从起始位置解析出来的元素
     */
    private ExpressionElement parseElement(String expression, int startIndex) {
        char baseElement = expression.charAt(startIndex);

        ExpressionElement result;

        if ('0' <= baseElement && baseElement <= '9') {
            Optional<Operand> resultOp = parseOperand(expression, startIndex);
            result = resultOp.orElseThrow(IllegalExpression::new);
            operandList.add((Operand) result);
        } else {
            Optional<Operator> resultOp = parseOperator(expression, startIndex);
            result = resultOp.orElseThrow(IllegalExpression::new);
            operatorList.add((Operator) result);
        }
        if (Objects.isNull(head)) {
            head = result;
            tail = head;
        }
        tail.setNext(result);
        tail = tail.getNext();
        return result;
    }

    /**
     * 解析操作数
     *
     * @param expression 表达式字符串
     * @param startIndex 解析的起始位置
     * @return 解析出来的操作数
     */
    private Optional<Operand> parseOperand(String expression, int startIndex) {
        char baseElement = expression.charAt(startIndex);
        if ('0' <= baseElement && baseElement <= '9') {
            int index = startIndex;
            StringBuilder sb = new StringBuilder();
            //操作数的长度不定，要一直解析到不是数字的位置
            while ('0' <= baseElement && baseElement <= '9' && index + 1 < expression.length()) {
                sb.append(baseElement);
                baseElement = expression.charAt(++index);
            }
            //防止丢失最后一位数字
            if ('0' <= baseElement && baseElement <= '9' && index + 1 >= expression.length()) {
                sb.append(baseElement);
            }
            return Optional.of(new Operand(sb.toString()));
        }
        return Optional.empty();
    }

    /**
     * 解析运算符
     *
     * @param expression 表达式字符串
     * @param startIndex 解析的起始位置
     * @return 解析出来的运算符
     */
    private Optional<Operator> parseOperator(String expression, int startIndex) {
        char baseElement = expression.charAt(startIndex);
        switch (baseElement) {
            case '+':
                return Optional.of(Plus.getInstance());
            case '-':
                return Optional.of(Subtract.getInstance());
            case '*':
                return Optional.of(Multiply.getInstance());
            case '/':
                return Optional.of(Divide.getInstance());
            case '(':
                return Optional.of(LeftBracket.getInstance());
            case ')':
                return Optional.of(RightBracket.getInstance());
        }
        return Optional.empty();
    }

    /**
     * 计算表达式结果
     *
     * @param expression 表达式的字符串
     * @return 结算结果
     */
    private double calculate(String expression) {
        expression = expression.trim();

        Stack<Operand> operandStack = new Stack<>();//数字栈
        Stack<Operator> operatorStack = new Stack<>();//符号栈

        int index = 0;//记录已经执行的符号数

        while (index < expression.length()) {

            ExpressionElement element = parseElement(expression, index);
            //如果是操作数，则直接添加
            if (element instanceof Operand) {
                operandStack.push((Operand) element);
                index += element.getOriginStr().length();
                continue;
            }
            //如果是左括号，则直接添加
            if (element.equals(LeftBracket.getInstance())) {
                operatorStack.push((Operator) element);
                index++;
                continue;
            }
            //如果是右括号，则一直计算到与之匹配的上一个左括号
            if (element.equals(RightBracket.getInstance())) {
                Operator operator = operatorStack.pop();
                while (!operator.equals(LeftBracket.getInstance())) {
                    calFromStack(operator, operandStack);
                    operator = operatorStack.pop();
                }
                index++;
                continue;
            }
            //如果是计算运算符，则根据优先级来操作
            if (element.equals(Plus.getInstance())
                    || element.equals(Subtract.getInstance())
                    || element.equals(Multiply.getInstance())
                    || element.equals(Divide.getInstance())) {

                Operator operator = (Operator) element;
                if (!operatorStack.isEmpty()) {
                    Operator previousOperator = operatorStack.peek();
                    //一直将该运算符前面比它优先级高的远算符计算完
                    while (operator.compareTo(previousOperator) <= 0) {
                        calFromStack(previousOperator, operandStack);
                        previousOperator = operatorStack.pop();
                    }
                }
                operatorStack.push(operator);
                index++;
            }
        }
        //处理剩余的计算，直到运算符栈为空，也就是计算完成
        while (!operatorStack.isEmpty()) {
            Operator operator = operatorStack.pop();
            calFromStack(operator, operandStack);
        }

        return operandStack.pop().getValue();
    }

    /**
     * 从操作数栈中弹出运算数，使用运算符进行计算，然后再将结果压入操作数栈中
     *
     * @param operator     运算符
     * @param operandStack 操作数栈
     */
    private void calFromStack(Operator operator, Stack<Operand> operandStack) {
        Operand rightOperand = operandStack.pop();
        Operand leftOperand = operandStack.pop();
        operandStack.push(operator.calculate(leftOperand, rightOperand));
    }

    public String getOrigin() {
        return origin;
    }

    public ExpressionElement getHead() {
        return head;
    }

    public List<Operand> getOperandList() {
        return operandList;
    }

    public List<Operator> getOperatorList() {
        return operatorList;
    }

    public double getComputeResult() {
        return computeResult;
    }

    class IllegalExpression extends RuntimeException {
        IllegalExpression() {
            super("expression is illegal");
        }
    }
}