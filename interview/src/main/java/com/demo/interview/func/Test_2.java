/*
 * Copyright (C), 2013-2019, 天津大海云科技有限公司
 */
package com.demo.interview.func;

/**
 * @author lidai
 * @date 2019/6/18 17:26
 * <p>
 * 有n个台阶，每次只能上一步或者两步，总共有多少种走法
 * <p>
 * 此类题型，应先推导出公式
 * <p>
 * n=1时，f(1)=1
 * n=2时，f(2)=2
 * n=3时，f(3) = f(1)+f(2)
 * n=4时，f(4) = f(2)+f(3)
 * ...
 * n=x时,f(x) = f(x-2)+f(x-1)
 */
public class Test_2 {

    public static void main(String[] args) {
        System.out.println(f(20));
    }

    public static int f(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return f(n - 2) + f(n - 1);
    }
}

