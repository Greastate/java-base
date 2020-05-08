/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */
package expression.element;

/**
 * <p>表达式的操作数元素</p>
 *
 * @author @leiguowei@meituan.com
 * @version $Id: Operand.java, v 0.1 2018-10-18 10:44 PM @leiguowei $$
 */
public class Operand extends ExpressionElement {
    private double value;

    public Operand(String origin) {
        super(origin);
        value = Double.valueOf(origin);
    }

    public Operand(double value) {
        super(String.valueOf(value));
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Operand plus(Operand rightOperand) {
        return new Operand(value + rightOperand.value);
    }

    public Operand subtract(Operand rightOperand) {
        return new Operand(value - rightOperand.value);
    }

    public Operand multiply(Operand rightOperand) {
        return new Operand(value * rightOperand.value);
    }

    public Operand divide(Operand rightOperand) {
        return new Operand(value / rightOperand.value);
    }
}