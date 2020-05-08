/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */
package expression.element.operator.impl;

import expression.element.Operand;
import expression.element.operator.OperatorComputeException;
import expression.element.operator.Operator;

import java.util.Objects;

/**
 * <p>左括号</p>
 *
 * @author @leiguowei@meituan.com
 * @version $Id: LeftBracket.java, v 0.1 2018-10-19 10:36 AM @leiguowei $$
 */
public class LeftBracket extends Operator {

    private static LeftBracket instance = new LeftBracket();

    private LeftBracket() {
        super('(');
    }

    public static LeftBracket getInstance() {
        return instance;
    }

    /**
     * 括号不具有运算能力
     *
     * @param left 左操作数
     * @param right 右操作数
     * @return 直接抛出异常
     */
    @Override
    public Operand calculate(Operand left, Operand right) {
        throw new OperatorComputeException();
    }

    @Override
    public int compareTo(Operator o) {
        if (Objects.isNull(o)) {
            throw new NullPointerException();
        }
        switch (o.getOriginStr().charAt(0)) {
            case '+':
            case '-':
            case '*':
            case '/':
                return -1;
            case ')':
                return 1;
            case '(':
                return 0;
            default:
                throw new IllegalArgumentException();
        }
    }
}