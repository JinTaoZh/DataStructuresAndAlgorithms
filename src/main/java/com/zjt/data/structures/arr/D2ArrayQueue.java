package com.zjt.data.structures.arr;

import java.util.Scanner;

/**
 * 数组模拟队列
 * <pre>
 *     基本介绍
 *         1、队列是一个有序列表，可以用数组或者链表来实现。
 *         2、遵循先入先出原则，即先存入的数据要先取出，后存入的数据要后取出。
 *      实现思路
 *          声明两个指针，rear和front两个变量，初始值是-1，rear是队首，front是队尾。
 *          当有数组加入时rear增加front不变，取出数据时rear不变。
 *       简要说明
 *          1、队列本身是有序列表，若使用数组的结构来存储队列的数据，则数组需要声明最大容量maxSize、rear队首、front队尾，声明rear 和 front
 *              的原因是入队总是队尾、出队是队首出队
 * </pre>
 * ssh
 *
 * @author zjt
 * @date 2021-07-09
 */
public class D2ArrayQueue {

    private int maxSize;  // 表示数组的最大容量

    private int front; // 队列的头部

    private int rear; // 队列的尾部

    private int[] arr; // 数组用于存放数据

    public D2ArrayQueue(int arrMaxSize) {
        if (arrMaxSize < 3) { //数组最小长度是 3
            arrMaxSize = 3;
        }
        maxSize = arrMaxSize;
        arr = new int[arrMaxSize];
        front = -1;// 指向队列的头部， 指向队列头的前一个位置
        rear = -1; // 指向队列尾部，指向队列尾的数据，也就是队列的最后一个数据
    }

    private boolean isFull() { // 判断队列是否已经满了
        return rear == maxSize - 1;
    }

    private boolean isEmpty() { // 判读队列是否为空
        return front == rear;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已经满了");
            return;
        }
        rear++; // rear 后移
        arr[rear] = n;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的");
        }
        front++;
        return arr[front];
    }

    // 显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(String.format("arr[%d] = %d", i, arr[i]));
        }
    }

    // 显示队列的头数据 不是取出数据
    public int getHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front + 1];
    }

}

class TestArrayQueue {

    public static void main(String[] args) {
        // 创建一个队列
//        D2ArrayQueue queue = new D2ArrayQueue(5);
        RingArrayQueue<Integer> queue = new RingArrayQueue<>(5);
        char key = ' ';//用于接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("s(show) 显示队列");
            System.out.println("e(exit) 退出程序");
            System.out.println("a(add) 添加元素");
            System.out.println("g(get) 从队列取出数据");
            System.out.println("h(head) 查看队列的头数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    flag = false;
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int i = scanner.nextInt();
                    queue.addQueue(i);
                    break;
                case 'g':
                    try {
                        int result = queue.getQueue(); // 处理一下异常 避免程序直接结束
                        System.out.println("取出的数据为：" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int result = queue.getHead();
                        System.out.println("查看队列头的数据：" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

}
