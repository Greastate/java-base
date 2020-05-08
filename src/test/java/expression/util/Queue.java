/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */
package expression.util;

/**
 * <p></p>
 *
 * @author @leiguowei@meituan.com
 * @version $Id: Queue.java, v 0.1 2018-10-18 10:06 PM @leiguowei $$
 */
public interface Queue<T> {
    void EnQueue(T element);

    T DeQueue();

    T MaxElement();
}