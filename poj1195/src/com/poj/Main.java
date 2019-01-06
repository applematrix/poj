package com.poj;

import java.util.Scanner;

public class Main {
    public static int N = 0;
    public static int[][] C = null;

    public static int lowbit(int x) {
        return (x & (-x));
    }

    public static void add(int x, int y, int v) {
        for (int i = x; i <= N; i += lowbit(i)) {
            for (int j = y; j <= N; j += lowbit(j)) {
                C[i][j] += v;
            }
        }
    }

    public static int sum(int x, int y) {
        int s = 0;
        for (int i = x; i >= 1; i -= lowbit(i)) {
            for (int j = y; j >= 1; j -= lowbit(j)) {
                s += C[i][j];
            }
        }
        return s;
    }

    public static void main(String[] args) {
	// write your code here
        Scanner cin = new Scanner(System.in);
        int start = cin.nextInt();
        N = cin.nextInt();
        C = new int[N+2][N+2];
        while (true) {
            int op = cin.nextInt();
            boolean quit = false;
            switch (op) {
                case 1:
                    int x = cin.nextInt();
                    int y = cin.nextInt();
                    int a = cin.nextInt();
                    add(x+1,y+1,a); //index start from 0;
                    break;
                case 2:
                    int x1 = cin.nextInt();
                    int y1 = cin.nextInt();
                    int x2 = cin.nextInt();
                    int y2 = cin.nextInt();

                    //index start from 0;
                    int sum = sum(x2+1,y2+1) - sum(x1, y2+1) - sum(x2+1,y1) + sum(x1, y1);
                    System.out.println(sum);
                    break;
                case 3:
                    quit = true;
                    break;
            }
            if (quit) {
                break;
            }
        }
    }
}
