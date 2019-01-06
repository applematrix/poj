package com.poj;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static class Village {
        public int left;
        public int right;
        public boolean destroyed = false;

        public Village() {
            left = 1;
            right = N;
        }

        public int connectedVillages() {
            if (destroyed) return 0;
            return right - left + 1;
        }
    }

    public static Village[] villages = null;
    public static int N = 0;

    public static void destory(int x) {
        villages[x].destroyed = true;

        for (int i = x + 1; i <= N; i++) {
            if (villages[i].destroyed) {
                break;
            }
            villages[i].left = x + 1;
        }

        for (int i = x - 1; i > 0; i--) {
            if (villages[i].destroyed) {
                break;
            }
            villages[i].right = x - 1;
        }
    }

    public static void restore(int x) {
        villages[x].destroyed = false;
        int left = x;
        int right = x;
        if (x - 1 > 0 && !villages[x - 1].destroyed) {
            left = villages[x - 1].left;
        }

        if (x + 1 <= N && !villages[x + 1].destroyed) {
            right = villages[x + 1].right;
        }

        for (int i = x + 1; i <= N; i++) {
            if (villages[i].destroyed) {
                break;
            }
            villages[i].left = left;
        }

        for (int i = x - 1; i > 0; i--) {
            if (villages[i].destroyed) {
                break;
            }
            villages[i].right = right;
        }
    }

    public static void main(String[] args) {
        // write your code here
        Scanner cin = new Scanner(System.in);
        N = cin.nextInt();
        int events = cin.nextInt();
        villages = new Village[N + 1];
        for (int i = 0; i <= N; i++) {
            villages[i] = new Village();
        }

        LinkedList<Integer> lastDestory = new LinkedList<Integer>();
        while (events > 0) {
            events--;
            String operation = cin.next();
            boolean quit = false;
            switch (operation.charAt(0)) {
                case 'D':
                    int x = Integer.valueOf(cin.next());
                    lastDestory.push(x);
                    destory(x);
                    break;
                case 'Q':
                    x = Integer.valueOf(cin.next());
                    System.out.println(villages[x].connectedVillages());
                    break;
                case 'R':
                    restore(lastDestory.pop());
                    break;
            }
        }
    }
}
