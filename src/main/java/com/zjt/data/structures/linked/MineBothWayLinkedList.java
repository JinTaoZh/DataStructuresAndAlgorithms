package com.zjt.data.structures.linked;

import java.util.LinkedList;

/**
 * 双向链表
 *
 * @author zjt
 * @date 2021-09-02
 */
public class MineBothWayLinkedList<E> {

    // 声明一个头节点 跟上面的单链表有所区别 头节点存储数据
    transient Node<E> head;

    // 声明一个尾节点 目的是使用起来更加方便
    transient Node<E> tail;

    // 这里新增时使用 ++ 删除时使用-- 没有考虑线程安全问题
    transient int size;

    public MineBothWayLinkedList() { // 构造器
    }

    // 尾插法新增
    public void addTail(E e) {
        // 取出尾节点
        Node<E> t = this.tail;
        // 新建节点
        Node<E> newNode = new Node<>(t, e, null);
        // 将新节点作为尾节点存储
        tail = newNode;
        if (t == null) { // 新增时链表为空
            head = newNode; // 赋值头节点
        } else {
            t.next = newNode; // 将之前的尾节点的next 指向新的节点
        }
        size++;
    }

    // 头插法新增
    public void addHead(E e) {
        // 取出头节点
        Node<E> h = this.head;
        // 新建节点
        Node<E> newNode = new Node<>(null, e, h);
        // 新的节点作为头节点
        head = newNode;
        if (h == null){ // 头节点为空，表示空链表
           tail = newNode;
        }else {
            h.prev = newNode; // 将之前的头节点的 前驱节点指向 新的头节点
        }
    }


    public int getSize() {
        return size;
    }

    private static class Node<E> { // 静态内部类用于处处数据节点

        E item; // 存储的数据

        Node<E> next; // 下一个节点的引用

        Node<E> prev;// 上一个节点的引用

        // 构造器
        protected Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        @Override  // 显示方便
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    '}';
        }
    }

}

class BothClient {

    public static void main(String[] args) {
        MineBothWayLinkedList<Integer> list = new MineBothWayLinkedList<>();
        list.addHead(1);
        list.addHead(2);
        list.addHead(3);
        list.addTail(4);
        System.out.println(list.head);
        System.out.println(list.tail);




    }

}
