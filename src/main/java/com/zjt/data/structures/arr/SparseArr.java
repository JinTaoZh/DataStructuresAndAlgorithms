package com.zjt.data.structures.arr;

import java.util.Arrays;

/**
 * 稀疏数组相关内容
 * <pre>
 *      当一个数组中大部分元素为0或者同一个值的数组时，可以使用稀疏数组来保存该数组。
 *      稀疏数组的处理方法是：
 *          1、记录数组一共有几行几列，有多少个不同的值。
 *          2、把具有不同值的元素的行列记录在一个小规模的数组中（它就是稀疏数组），从而缩小数组的规模。
 * </pre>
 *
 * @author zjt
 * @date 2021-07-09
 */
public class SparseArr {

    // 一个稀疏数组的应用案例
    public static void main(String[] args) throws Exception {
        int[][] arr = new int[10][10];
        arr[1][2] = 1;
        arr[2][3] = 2;
        arr[3][3] = 1;
        // 输出原始的二维数组
        System.out.println("输出一下原始的二维数组~~");
        for (int[] row : arr) {
            for (int data : row) {
                System.out.print(String.format("%d\t", data));
            }
            System.out.println();
        }

        SparseArr sparseArr = new SparseArr();
        int[][] sparseArray = sparseArr.toSparseArray(arr);
        sparseArr.toOrdinaryArr(sparseArray);
    }

    // 将二维数组转换成为稀疏数组 并保存
    // 此方法未考虑 arr为null的情况
    public int[][] toSparseArray(int[][] arr) {
        // 二维数组中有效的数据个数，这里按照最简单的大于0 就算是有效的
        int totalValidNum = Arrays.stream(arr)
                .mapToInt(ints -> (int) Arrays.stream(ints).filter(i -> i > 0).count()).sum();
        // 创建对应的稀疏数组
        int[][] sparseArr = new int[totalValidNum + 1][3];
        int row = arr.length; // 二维数组的行数
        int col = arr[0].length; // 二维数组的列数
        // 给稀疏数组的第一行赋值
        sparseArr[0][0] = row; // 第一行第一列是原来二维数组行数
        sparseArr[0][1] = col; // 第一行第二列是原来二维数组列数
        sparseArr[0][2] = totalValidNum; // 第一行第三列是原来二维数组中有效数据的个数
        // 从第二行开始
        // 遍历二维数组将非0的值赋值到稀疏数组中
        int count = 0; // 用于记录是第几个非0数据 对应稀疏数组中的行数
        for (int i = 0; i < arr.length; i++) {
            int[] data = arr[i];
            for (int j = 0; j < data.length; j++) {
                if (data[j] != 0) {
                    count++; // 稀疏数组必须从第二列开始，必须先++
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = data[j];
                }
            }
        }
        // 得到的稀疏数组的形式
        System.out.println("输出一下获取的稀疏数组~~");
        for (int[] data : sparseArr) {
            System.out.println(String.format("%d\t%d\t%d\t", data[0], data[1], data[2])); // 打印一下稀疏数组
        }
        return sparseArr;
    }

    // 读取并将稀疏数组转换为二维数组
    public int[][] toOrdinaryArr(int[][] sparseArray) {
        // 稀疏数组的第一行存储的是 行数 列数 有效数据总个数
        int[][] arr = new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int i = 1; i < sparseArray.length; i++) {
            int[] data = sparseArray[i];
            arr[data[0]][data[1]] = data[2];
        }
        System.out.println("输出转换后的二维数组~~");
        Arrays.stream(arr).forEach(row -> {
            Arrays.stream(row).forEach(item -> {
                System.out.print(String.format("%d\t", item));
            });
            System.out.println();
        });
//        for (int[] row : arr) {
//            for (int data : row) {
//                System.out.print(String.format("%d\t", data));
//            }
//            System.out.println();
//        }
        return arr;
    }

    //     // 小案例 将棋盘数据保存 并恢复
    //     public static void writeAndReadArr() throws Exception {
    //        // 先创建一个原始的二维数组 10 * 10
    //        // 0 表示没有棋子 1 表示黑色 2表示 白色
    //        int[][] chessArr = new int[10][10];
    //        chessArr[1][2] = 1;
    //        chessArr[2][3] = 2;
    //        chessArr[3][3] = 1;
    //        // 输出原始的二维数组
    //        System.out.println("输出原始的二维数组~~");
    //        for (int[] row : chessArr) {
    //            for (int data : row) {
    //                System.out.print(String.format("%d\t", data));
    //            }
    //            System.out.println();
    //        }
    //        // 将二维数组转成稀疏数组
    //        // 1、遍历二维数组 得到非0数据的个数
    //        int sum = 0;
    //        for (int[] row : chessArr) {
    //            for (int data : row) {
    //                if (data > 0) {
    //                    sum++;
    //                }
    //            }
    //        }
    //
    //        System.out.println("-------------------------------");
    //        System.out.println("------二维数组转稀疏数组---------");
    //
    //        // 2、创建对应的稀疏数组
    //        int[][] sparseArr = new int[sum + 1][3];
    //        // 给稀疏数组赋值
    //        sparseArr[0][0] = 10;
    //        sparseArr[0][1] = 10;
    //        sparseArr[0][2] = sum;
    //        // 遍历二维数组将非0的值赋值到稀疏数组中
    //        // count 用于记录是第几个非0数据
    //        int count = 0;
    //        for (int i = 0; i < chessArr.length; i++) {
    //            int[] data = chessArr[i];
    //            for (int j = 0; j < data.length; j++) {
    //                if (data[j] != 0) {
    //                    count++;
    //                    sparseArr[count][0] = i;
    //                    sparseArr[count][1] = j;
    //                    sparseArr[count][2] = data[j];
    //                }
    //            }
    //        }
    //        // 得到的稀疏数组的形式
    //        System.out.println();
    //        System.out.println("得到的稀疏数组的形式");
    //        for (int[] data : sparseArr) {
    //            System.out.println(String.format("%d\t%d\t%d\t", data[0], data[1], data[2]));
    //        }
    //
    //        System.out.println("-------------------------------");
    //        System.out.println("------稀疏数组转二维数组---------");
    //        // 稀疏数组的第一行存储的是 行数 列数 有效数据总个数
    //        int[][] chessArr1 = new int[sparseArr[0][0]][sparseArr[0][1]];
    //        for (int i = 1; i < sparseArr.length; i++) {
    //            int[] data = sparseArr[i];
    //            chessArr1[data[0]][data[1]] = data[2];
    //        }
    //        System.out.println("输出转换后的二维数组~~");
    //        for (int[] row : chessArr1) {
    //            for (int data : row) {
    //                System.out.print(String.format("%d\t", data));
    //            }
    //            System.out.println();
    //        }
    //
    //        // 将获取到的稀疏数组写出到磁盘
    //        // 起到一个保存的作用
    //        System.out.println("-------------------------------");
    //        System.out.println("将获取到的稀疏数组写出到磁盘");
    //        File file = new File("/Users/zhangjintao/Desktop/arr.txt");
    //        if (!file.exists()) {
    //            boolean newFile = file.createNewFile();
    //            if (!newFile) {
    //                System.out.println("文件创建失败");
    //                return;
    //            }
    //        }
    //        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
    //        StringBuilder sb = new StringBuilder();
    //        for (int[] data : sparseArr) { // 数组转成字符串存储
    //            for (int datum : data) {
    //                sb.append(datum).append("\t");
    //            }
    //            sb.append("\n");
    //        }
    //        bw.write(String.valueOf(sb));
    //        bw.flush();
    //        bw.close();
    //
    //        System.out.println("-------------------------------");
    //        System.out.println("从磁盘读取数组");
    //        // 从磁盘读取数组
    //        int[][] sArr;
    //        BufferedReader br = new BufferedReader(new FileReader("/Users/zhangjintao/Desktop/arr.txt"));
    //        // 第一行数据 存储的是 原始二维数组的 行列数 有效值的个数
    //        String line = br.readLine();
    //        String[] arr = line.split("\t");
    //        sArr = new int[Integer.parseInt(arr[0])][Integer.parseInt(arr[1])];
    //        while ((line = br.readLine()) != null) {
    //            String[] s2 = line.split("\t");
    //            sArr[Integer.parseInt(s2[0])][Integer.parseInt(s2[1])] = Integer.parseInt(s2[2]);
    //        }
    //        br.close();
    //        for (int[] row : sArr) {
    //            for (int data : row) {
    //                System.out.print(String.format("%d\t", data));
    //            }
    //            System.out.println();
    //        }
    //    }

}
