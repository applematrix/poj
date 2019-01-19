package com.poj;

import java.util.Scanner;

public class Main {
    public static long[] upper = null;//剪枝优化，当前子树能够表示的最大值
    public static long[] floor = null;//剪枝优化，当前子树能够表示的最小值
    public static String strSign = null;
    public static long[] values = null;
    public static int[] result = null;
    public static int N = 0;

    public static void init() {
        upper = new long[N+1];
        floor = new long[N+1];
        result = new int[N];
        values = new long[N];

        int k = N - 1;
        int sum = 0;

        upper[N] = 0;
        long curMaskValue = 1;
        while (k >= 0) {
            if (strSign.charAt(k) == 'p') {
                upper[k] = curMaskValue + upper[k+1];
                floor[k] = floor[k+1];
                values[k] = curMaskValue;
            } else {
                upper[k] = upper[k+1];
                floor[k] = -curMaskValue + floor[k+1];
                values[k] = -curMaskValue;
            }
            curMaskValue <<= 1;
            result[k] = -1;
            k--;
        }
        if (N == 64) {
            if (strSign.charAt(0) == 'p') {
                upper[0] = Long.MAX_VALUE;
            } else {
                floor[0] = Long.MIN_VALUE;
            }
        }
    }

    public static boolean checkFunk(long value) {
        int depth = 0;
        long remind = value;
        while(true) {
            if (depth == -1) {
                return false;
            }

            //遍历到树叶
            if (depth > N) {
                if (remind == 0) {
                    return true;
                }
            } else if(remind < floor[depth] || remind > upper[depth]) {//剪枝
                //将当前的值恢复为遍历前的值
                if (depth < N) {
                    if (result[depth] != -1) {
                        remind += result[depth]*values[depth];
                    }
                    result[depth] = -1;
                }
                depth--;
                continue;
            }

            //初始值为-1，每个节点可以选择0，也可以选择1，实际上是两个子树
            // ++操作将先遍历0，再遍历1的分支
            result[depth]++;
            if (result[depth] == 0) {
                //当前节点选择0，不更新remind值
                depth++;
            } else if (result[depth] == 1) {
                //当前节点选择1，剩下的值需要减掉当前深度的值
                remind -= values[depth];
                depth++;

                if (remind == 0) {
                    for (int i = depth; i < N; i++) {
                        result[i] = 0;
                    }
                    return true;
                }
            } else if (result[depth] == 2) {
                //当前的0,1分支都没有遍历到叶节点，回溯到上一层
                result[depth] = -1;
                remind += values[depth];
                depth--;
            }
        }
    }

    public static String formatResult() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < N; i++) {
            if (result[i] == 1) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int testCases = cin.nextInt();
        while (testCases-- > 0) {
            N = cin.nextInt();
            strSign = cin.next();
            init();

            long value = cin.nextLong();
            if (value == 0) {
                StringBuffer sb = new StringBuffer(N);
                for (int i = 0; i < N; i++) {
                    sb.append('0');
                }
                System.out.println(sb.toString());
            }else if (!checkFunk(value)) {
                System.out.println("Impossible");
            } else {
                System.out.println(formatResult());
            }
        }
    }
}
