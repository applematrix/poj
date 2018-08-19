package com.poj;

import java.util.Scanner;

public class Main {

    public static boolean checksubString(String s, String t){
        if (s.length() > t.length()) {
            return false;
        }
        int j = 0;
        for (int i=0; i<t.length(); i++) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
                if (j == s.length()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner cin=new Scanner(System.in);
        String input = cin.nextLine();
        while(input != null ) {
            if (input.length() == 0) {
                break;
            }

            String[] testCase = input.split(" ");
            if (checksubString(testCase[0], testCase[1])) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
            input = cin.nextLine();
        }
    }
}
