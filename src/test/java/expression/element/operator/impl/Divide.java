/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */
package expression.element.operator.impl;

import expression.element.Operand;
import expression.element.operator.Operator;
import expression.element.operator.OperatorComputeException;

import java.util.Objects;

/**
 * <p>除法运算符</p>
 *
 * @author @leiguowei@meituan.com
 * @version $Id: Divide.java, v 0.1 2018-10-18 10:56 PM @leiguowei $$
 */
public class Divide extends Operator {

    private static Divide instance = new Divide();

    private Divide() {
        super('/');
    }

    public static Divide getInstance() {
        return instance;
    }

    @Override
    public Operand calculate(Operand left, Operand right) {
        //捕捉除零操作
        if (right.equals(new Operand(0))) {
            throw new OperatorComputeException();
        }
        return left.divide(right);
    }

    @Override
    public int compareTo(Operator o) {
        if (Objects.isNull(o)) {
            throw new NullPointerException();
        }
        switch (o.getOriginStr().charAt(0)) {
            case '+':
            case '-':
            case ')':
            case '(':
                return 1;
            case '/':
            case '*':
                return 0;
            default:
                throw new IllegalArgumentException();
        }
    }
}