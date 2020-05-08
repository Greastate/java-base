/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */
package expression.element.operator.impl;

import expression.element.Operand;
import expression.element.operator.Operator;

import java.util.Objects;

/**
 * <p>加法运算符</p>
 *
 * @author @leiguowei@meituan.com
 * @version $Id: Plus.java, v 0.1 2018-10-18 10:49 PM @leiguowei $$
 */
public class Plus extends Operator {

    private static Plus instance = new Plus();

    private Plus() {
        super('+');
    }

    public static Plus getInstance() {
        return instance;
    }

    @Override
    public Operand calculate(Operand left, Operand right) {
        return left.plus(right);
    }

    @Override
    public int compareTo(Operator o) {
        if (Objects.isNull(o)) {
            throw new NullPointerException();
        }
        switch (o.getOriginStr().charAt(0)) {
            case ')':
            case '(':
                return 1;
            case '-':
            case '+':
                return 0;
            case '*':
            case '/':
                return -1;
            default:
                throw new IllegalArgumentException();
        }
    }
}