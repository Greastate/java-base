/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */
package expression.element;

/**
 * <p>表达式的元素</p>
 *
 * @author @leiguowei@meituan.com
 * @version $Id: ExpressionElement.java, v 0.1 2018-10-18 11:04 PM @leiguowei $$
 */
public abstract class ExpressionElement {
    /**
     * 该元素的下一个元素
     */
    protected ExpressionElement next;
    /**
     * 该元素的字符串形式
     */
    protected String originStr;

    public ExpressionElement(String originStr) {
        this.originStr = originStr;
    }

    public ExpressionElement getNext() {
        return next;
    }

    public void setNext(ExpressionElement next) {
        this.next = next;
    }

    public String getOriginStr() {
        return originStr;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExpressionElement)) {
            return false;
        }
        return ((ExpressionElement) obj).getOriginStr().equals(this.originStr);
    }

    @Override
    public String toString() {
        return "ExpressionElement{" +
                "originStr='" + originStr + '\'' +
                '}';
    }
}