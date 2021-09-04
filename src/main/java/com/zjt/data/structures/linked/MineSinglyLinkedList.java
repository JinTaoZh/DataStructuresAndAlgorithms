package com.zjt.data.structures.linked;

import com.zjt.data.utils.ICollectionUtils;

import java.util.Stack;

/**
 * 单向链表
 * 实现一个单向链表->完成单链表的增加、删除、修改、查找
 * <pre>
 *   链表是有序的，但是存储地址不一定是有序的，需要定义一个头指针(head),存储地址、数据域、next域
 *     1、链表是以节点的方式存储的
 *     2、每个节点包含data域(存储数据)、next域指向的下一个存储的节点
 *     3、链表的各个节点不一定是连续存放的
 *     4、链表可以有头节点，也可以不包含头节点
 *
 *
 * <pre/>
 * @author zjt
 * @date 2021-08-27
 */
public class MineSinglyLinkedList<E> {

    // 头节点 已最简单的方式存储，不存放具体的数据，就是表示单链表的头，也不要动，仅表示一个链表的头部
    transient Node<E> head = new Node<>(null, null);

    /**
     * 新增一个节点到单链表 、这里仅实现在尾部添加
     * 将最后一个节点的next 指向新的节点
     *
     * @param e 具体的元素
     */
    public void add(E e) {
        // 需要一个临时变量 temp 用于遍历链表，找到最后一个节点
        Node<E> temp = this.head;
        // 遍历链表 找到链表的尾节点
        while (temp.next != null) {
            // 有元素存在指针后移
            temp = temp.next;
        }
        temp.next = new Node<>(e);
    }

    /**
     * 指定下标插入元素
     *
     * @param index 指定的下标
     * @param e     具体的元素
     */
    public void add(int index, E e) {
        // 头部节点不动  依旧通过辅助变量temp来帮助找到需要添加的位置
        Node<E> temp = this.head;
        for (int i = 0; i < index; i++) {
            // 因为是单链表 所以找到的temp是位于 添加位置的前一个节点 否则添加会出现问题
            if ((temp = temp.next) == null) {
                throw new IndexOutOfBoundsException(String.format("Index:%d, Size:%d", index, i));
            }
        }
        // 插入到链表中
        temp.next = new Node<>(e, temp.next);
    }

    /**
     * 删除节点
     * 1、找到待删除节点的前一个节点
     * 2、临时变量 e == temp.next.item
     * 3、被删除的节点 将不会有其它引用指向，会被垃圾回收机制回收
     *
     * @param e 需要删除的元素
     */
    public void remove(E e) {
        if (head.next == null) {
            throw new RuntimeException("链表为空");
        }
        Node<E> temp = head;
        while (temp.next != null) {
            // 找到待删除节点的前一个节点
            if (e.equals(temp.next.item)) {
                temp.next = temp = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
    }

    // 显示链表
    public void show() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 头节点不要动 需要一个辅助变量遍历
        Node<E> temp = head.next;
        while (temp != null) {
            // 输出节点的信息
            System.out.println(temp);
            // 将temp后移
            temp = temp.next;
        }
    }

    /*
     * 单链表的反转
     *  思路 新建链表 使用头插法
     */
    public void reverseList() {
        if (head.next == null || head.next.next == null) {
            // 当前链表为空 或者只有一个节点时无需反转
            return;
        }
        // 声明一个临时变量
        Node<E> temp = head.next;
        // 清空当前链表
        head = new Node<>(null);
        while (temp != null) {
            addHead(temp.item);
            temp = temp.next;
        }
    }

    // 单链表头插法、每次新增都在链表的第一个位置
    public void addHead(E e) {
        Node<E> temp = head.next;
        head.next = new Node<>(e, temp);
    }

    // 单链表反转 思路二 类似于头插法
    public void reverseList1() {
        if (head.next == null || head.next.next == null) {
            // 当前链表为空 或者只有一个节点时无需反转
            return;
        }
        Node<E> current = head.next; // 当前节点辅助指针 用于遍历链表
        Node<E> next; // 指向当前节点[current]的下一个节点
        Node<E> reverseNode = new Node<>(null); // 设置一个反转链表新的头节点
        // 遍历原来的链表、每遍历一个节点 就将其取出并放在新链表的 reverseNode 的头节点
        while (current != null) {
            next = current.next; // 暂时保存当前节点的下一个节点
            current.next = reverseNode.next; // 将current的下一个节点 指向新的链表的最前端
            reverseNode.next = current; // 将 反转的头节点下一个节点 指向 当前节点 current 连接到新的链表上
            current = next; // 指针后移
        }
        head.next = reverseNode.next;
    }

    // 单链表反转以上

    // 逆序打印单向链表

    // 思路一：根据上面实现的单链表反转方法，先反转再打印。缺点：破坏了原有的链表结构
    // 思路二：利用递归的方式打印 缺点 因为这里头节点仅作为引用 并不实际存放数据 所以直接调用打印会将头节点的null 打印出来
    private void printfListReverseByRecursive(Node<E> head) {
        if (head.next != null) {
            printfListReverseByRecursive(head.next);
        }
        System.out.println(head.item);
    }

    // 打印当前链表 从头节点的下一个节点开始打印
    public void printfListReverseByRecursive() {
        printfListReverseByRecursive(this.head.next);
    }

    // 思路三 使用栈的特性逆序打印
    public void printfListReverseByStack() {
        // 空链表 不打印
        if (head.next == null) {
            return;
        }
        // 遍历入栈
        Stack<E> stack = new Stack<>();
        Node<E> temp = head.next;
        while (temp != null) {
            stack.push(temp.item);
            temp = temp.next;
        }
        // 出栈
        while (ICollectionUtils.isNotEmpty(stack)) {
            System.out.println(stack.pop());
        }
    }
    // 逆序打印单向链表以上

    private static class Node<E> { // 静态内部类 用于存储数据节点

        E item; // 存储的数据

        Node<E> next; // 下一个节点的引用

        protected Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        protected Node(E item) {
            this.item = item;
            this.next = null;
        }

        @Override
        public String toString() { // 重写一个toString方法，显示方便
            return "Node{" +
                    "item=" + item +
                    '}';
        }
    }

}

class Client {

    public static void main(String[] args) {
        MineSinglyLinkedList<Integer> list = new MineSinglyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.show();
        System.out.println("----------");
        list.reverseList1();
        list.show();
        System.out.println("----------");
        list.printfListReverseByStack();
        System.out.println("----------");
        list.printfListReverseByRecursive();
    }

}
