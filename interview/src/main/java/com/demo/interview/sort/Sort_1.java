/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.sort;

/**
 * @author lidai
 * @date 2019/7/24 11:05
 * <p>
 * 排序方法
 */
public class Sort_1 {

    public static void main(String[] args) {
        int[] array = {89, 14, 7, 3, 9, 10, 43, 67};
//        System.out.println(Arrays.toString(sort_quickly(array)));
//        System.out.println(Arrays.toString(sort_mp(array)));
    }

    /**
     * 快速排序
     * <p>
     * 第一个数和所有数比较，第二个数和所有数比较...依次类推
     */
    public static int[] sort_quickly(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 冒泡排序
     * <p>
     * 第一个和第二个比较，第二个和第三个比较,将较大的一个数换到右边
     */
    public static int[] sort_mp(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }



}

