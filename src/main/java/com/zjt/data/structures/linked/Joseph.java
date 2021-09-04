package com.zjt.data.structures.linked;

/**
 * 约瑟夫环问题
 *
 * 约瑟夫问题，假设编号1，2，3 …n个人，约定号码k(1<=k<=n)的数字，从k的位置开始数，数到m的人出列，下一个人从1开始数，依此类推直到所有人出列，产生一个出队编号。
 *     假定 n =5 有5个人,k=1 第一个位置开始,m=2 每数到2的人出列。
 *
 * @author zjt
 * @date 2021-09-02
 */
public class Joseph {

    public static void main(String[] args) {
        RingLinkedList<Integer> list = new RingLinkedList<>();
        for (int i = 1; i <= 125 ; i++) {
            list.add(i);
        }
        System.out.println();
        list.show();
        list.getJoseph(10,20);
    }

}

class RingLinkedList<E> {

    private Node<E> head; // 头节点

    private Node<E> tail; // 尾节点

    private int size; // 链表的长度


    // 使用尾插法新增数据
    // 形成一个环状，让尾节点的next 指向头节点这样就形成一个环状
    public void add(E e) {
        // 当前的尾节点
        Node<E> t = this.tail;
        Node<E> newNode = new Node<>(e, null);
        // 将新节点作为尾节点
        tail = newNode;
        if (t == null) { // 新增时链表是空的
            // 赋值头节点
            head = newNode;
        } else {
            // 不是空链表
            // 将之前的尾节点的next 指向新的节点
            t.next = newNode;
        }
        size++;
        // 让尾节点的next 指向头节点
        tail.next = head;
    }

    // 遍历当前循环链表
    public void show() {
        Node<E> temp = this.head;
        if (temp == null) {
            System.out.println("空链表");
            return;
        }
        while (true) {
            System.out.println(temp.toString());
            if (temp.next == head) {
                break;
            }
            temp = temp.next;
        }
    }

    // 约瑟夫环出队问题  1 <= K <= this.size 表示从第几个开始 m 表示数几次出圈
    public void getJoseph(int k, int m) {
        if (head == null || k < 0 || k > size) {
            return;
        }
        // 创建临时变量 辅助约出圈问题
        Node<E> helper = head; // 指向头节点
        Node<E> temp = tail; // 指向尾节点 它的下一个节点是头节点
        // 出圈报数前先根据需求后移k-1次
        // 例如后移2
        for (int i = 0; i < k - 1; i++) {
            helper = helper.next;
            temp = temp.next;
        }
        // 当元素报数时，temp 和 helper需要后移 m-1次 然后出圈
        // 说明圈中仅有一个节点
        while (helper != temp) {
            for (int i = 0; i < m - 1; i++) {
                temp = temp.next;
                helper = helper.next;
            }
            // 此时temp 指向的节点 就是需要出圈的节点
            System.out.println("出圈的节点为：" + helper.item);
            // 将temp 指向的节点出圈
            helper = helper.next;
            temp.next = helper;
        }
        System.out.println("最后留在圈中的节点:" + helper.item);

    }

    private static class Node<E> {

        E item;

        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    '}';
        }
    }

}