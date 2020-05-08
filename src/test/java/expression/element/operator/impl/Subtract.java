/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */
package expression.element.operator.impl;

import expression.element.Operand;
import expression.element.operator.Operator;

import java.util.Objects;

/**
 * <p>减法运算符</p>
 *
 * @author @leiguowei@meituan.com
 * @version $Id: Subtract.java, v 0.1 2018-10-18 10:55 PM @leiguowei $$
 */
public class Subtract extends Operator {

    private static Subtract instance = new Subtract();

    private Subtract() {
        super('-');
    }

    public static Subtract getInstance() {
        return instance;
    }

    @Override
    public Operand calculate(Operand left, Operand right) {
        return left.subtract(right);
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
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return -1;
            default:
                throw new IllegalArgumentException();
        }
    }
}