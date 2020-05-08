/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */
package expression.element.operator;

/**
 * <p>运算符计算异常，比如使用括号来进行计算，除0计算</p>
 *
 * @author @leiguowei@meituan.com
 * @version $Id: OperatorComputeException.java, v 0.1 2018-10-19 10:51 AM @leiguowei $$
 */
public class OperatorComputeException extends RuntimeException {
    public OperatorComputeException() {
        super("Operator calculate error");
    }

    public OperatorComputeException(Exception e) {
        super("Operator calculate error: " + e.getMessage());
    }
}