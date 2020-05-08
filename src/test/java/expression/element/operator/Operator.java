/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */
package expression.element.operator;

import expression.element.ExpressionElement;
import expression.element.Operand;

/**
 * <p>表达式的运算符元素，提供计算能力和优先级比较能力</p>
 *
 * @author @leiguowei@meituan.com
 * @version $Id: Operator.java, v 0.1 2018-10-18 10:42 PM leiguowei Exp $$
 */
public abstract class Operator extends ExpressionElement implements Comparable<Operator> {

    public Operator(char value) {
        super(String.valueOf(value));
    }

    /**
     * 运算符元素需要提供计算能力
     *
     * @param left 左操作数
     * @param right 右操作数
     * @return 计算结果
     */
    public abstract Operand calculate(Operand left, Operand right);
}