/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */
package expression.util.impl;

import expression.util.Queue;

import java.util.Comparator;
import java.util.Objects;

/**
 * <p>能够获取最大值的简单队列</p>
 *
 * @author @leiguowei@meituan.com
 * @version $Id: MyQueue.java, v 0.1 2018-10-18 10:14 PM @leiguowei $$
 */
public class MyQueue<T> implements Queue<T> {
    private Comparator<T> comparator;
    private T maxElement;
    private Node<T> head = new Node<>();
    private Node<T> tail = head;

    /**
     * 队列能够存储通用数据，于是需要提供元素的比较器，以能够得到最大值
     *
     * @param comparator 比较器
     */
    public MyQueue(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void EnQueue(T element) {
        if (!Objects.isNull(maxElement) && comparator.compare(maxElement, element) < 0) {
            maxElement = element;
        }
        tail.data = element;
        tail.next = new Node<>();
        tail = tail.next;
    }

    @Override
    public T DeQueue() {
        if (head == tail) {
            return null;
        }
        T data = head.data;
        head = head.next;
        return data;
    }

    @Override
    public T MaxElement() {
        return maxElement;
    }

    private class Node<E> {
        E data;
        Node<E> next;
    }
}