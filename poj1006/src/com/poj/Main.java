package com.poj;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner cin=new Scanner(System.in);
        int caseNo = 0;
        while (true) {
            int p = cin.nextInt();
            int e = cin.nextInt();
            int i = cin.nextInt();
            int d = cin.nextInt();

            if (p == -1 && e == -1 && i == -1 && d == -1) {
                break;
            }

            if (p > d) {
                p = p - (p - d)/23 * 23;
                if (p > d) {
                    p -= 23;
                }
            }

            if (e > d) {
                e -= (e - d)/28 * 28;
                if (e > d) {
                    e -= 28;
                }
            }

            if (i > d) {
                i -= (i - d)/33 * 33;
                if (i > d) {
                    i -= 33;
                }
            }

            caseNo++;
            for (int k = 1 ; k <= 21252;) {
                int curDay =  d + k;

                int pMod = (curDay-p) % 23;
                int eMod = (curDay-e) % 28;
                int iMod = (curDay-i) % 33;

                int step = Math.min(Math.min(23-pMod, 28-eMod), 33-iMod);

                //System.out.println("Case "+caseNo+": check "+k+" days, next step is " + step);
                if (pMod == 0 && eMod == 0 && iMod == 0) {
                    System.out.println("Case "+caseNo+": the next triple peak occurs in "+k+" days.");
                    break;
                } else {
                    k += step;
                }
            }
        }
    }
}
