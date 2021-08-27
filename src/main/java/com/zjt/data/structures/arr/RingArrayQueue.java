package com.zjt.data.structures.arr;


/**
 * 环形队列
 * <pre>
 *     根据数组队列进行调整
 *      1、front指针的含义调整为->front指向队列的第一个元素，也就是arr[front]就是队列的第一个元素。
 *     2、real指针的含义调整为->rear指向队列的对后一个元素的后一个位置，原因是希望空出一个空间做约定。
 *     3、当队列满时，条件是 rear == maxSize-1 调整为 (rear +1) % maxSize == front 表示队列满了。
 *     4、 队列为空的条件是 rear == front 是空。
 *     5、rear 和 front 的初始值都为0。
 *     6、队列中有效的数据的个数 为 (rear + maxSize - front)%maxSize <==> Math.abs(rear-front) % maxSize
 *     7、可以将原来的队列修改转换成为环形队列
 * </pre>
 *
 * @author zjt
 * @date 2021-08-26
 */
public class RingArrayQueue<T> {

    private final int maxSize; // 表示数组的最大容量
    private final Object[] arr; // 数组用于存放数据 这里需要预留一个空的空间去应对环形的问题
    // front指针的含义调整为->front指向队列的第一个元素，也就是arr[front]就是队列的第一个元素。
    private int front; // 队列的头部 初始值为0
    // real指针的含义调整为->rear指向队列的对后一个元素的后一个位置，原因是希望空出一个空间做约定。
    private int rear; // 队列的尾部  初始值为0
    // 实际存放的元素个数 是 arrMaxSize-1 需要约定一个空的位置 而这个空的位置是会移动的。

    public RingArrayQueue(int arrMaxSize) {
        if (arrMaxSize < 4) {
            arrMaxSize = 4; // 最小长度为4
        }
        maxSize = arrMaxSize + 1;
        arr = new Object[maxSize];
    }

    private boolean isFull() { // 判断队列是否已经满了
        return (rear + 1) % maxSize == front; // 预留出一个空的空间 需要 +1
    }

    private boolean isEmpty() { // 判断队列是否为空
        return front == rear;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已经满了");
            return;
        }
        // 直接将数据加入
        arr[rear] = n;
        // 将rear后移 但是需要考虑环形 也就是取模
        rear = (rear + 1) % maxSize;
    }

    // 取出数据
    @SuppressWarnings("unchecked")
    public T getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列是空的");
        }
        // 这里需要分析出 front 是指向队列的第一个元素
        // 先把front 对应的值保留到一个临时变量 将front后移 然后将临时变量返回
        T value = (T) arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    // 显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        // 从 front 开始遍历 遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.println(String.format("arr[%d] = %s", i % maxSize, arr[i % maxSize]));
        }
    }

    // 求出当前队列有效数据的个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头数据 不是取出数据
    @SuppressWarnings("unchecked")
    public T getHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return (T) arr[front];
    }


}
