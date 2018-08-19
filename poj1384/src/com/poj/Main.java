package com.poj;

import java.util.Scanner;

public class Main {

    public static class Coin {
        public int value;
        public int weight;
        public Coin(int v, int w) {
            value = v;
            weight = w;
        }
    }

    public static long calcMinValue(int capacity, Coin[] coins) {
        if (capacity == 0) {
            return 0l;
        }
        long[] value = new long[capacity+1];
        for (int i =1; i <= capacity; i++) {
            value[i] = Integer.MAX_VALUE;
        }
        value[0] = 0;

        for (int i=0; i < coins.length; i++ ) {
            if (coins[i].weight > capacity) {
                continue;
            }
            if (value[coins[i].weight] > coins[i].value) {
                value[coins[i].weight] = coins[i].value;
            }
        }

        for (int i = 0; i <= capacity; i++) {
            if (value[i] == Integer.MAX_VALUE) {
                continue;
            }
            boolean updateValue = false;
            for (int j = 0; j < coins.length; j++) {
                int usedCapacity = i + coins[j].weight;
                if (usedCapacity <= capacity) {
                    if (value[i] + coins[j].value < value[usedCapacity] ) {
                        value[usedCapacity] = value[i] + coins[j].value;
                    }
                }
            }
        }
        return value[capacity];
    }

    public static void main(String[] args) {
        Scanner cin=new Scanner(System.in);
        int testCases = cin.nextInt();
        while (testCases > 0) {
            int emptyWeight = cin.nextInt();
            int fullWeight = cin.nextInt();

            int capacity = fullWeight - emptyWeight;

            int coinCount = cin.nextInt();
            Coin[] coins = new Coin[coinCount];
            for (int i = 0; i < coinCount; i++) {
                coins[i] = new Coin(cin.nextInt(), cin.nextInt());
            }

            long minValue = calcMinValue(capacity, coins);
            if (minValue == Integer.MAX_VALUE) {
                System.out.println("This is impossible.");
            } else {
                System.out.println("The minimum amount of money in the piggy-bank is "+minValue+".");
            }
            testCases--;
        }
    }
}
