package com.poj;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static String trim(String value) {
        int pos = value.indexOf('.');

        StringBuffer sb = new StringBuffer();
        int i = 0;
        while(i < value.length() && value.charAt(i) == '0') {
            i++;
        }
        sb.append(value.substring(i));

        if (sb.length() == 0) {
            return "0";
        }

        if (sb.indexOf(".") != -1) {
            i = sb.length() - 1;
            while (i >=0 && sb.charAt(i) == '0') {
                i--;
            }

            if (i >= 0 && sb.charAt(i) == '.') {
                i--;
            }
            if (i == 0) {
                return "0";
            }

            sb.setLength(i+1);
        }
        return sb.toString();
    }


    public static String multi(String op1, String op2) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        String left;
        String right;
        if (op1.length() > op2.length()) {
            left = op1;
            right = op2;
        } else {
            right = op2;
            left = op1;
        }

        ArrayList<Integer> leftInts = new ArrayList<Integer>();

        //左边操作数的小数点位数
        int leftDecimal = 0;
        for (int i=0; i < left.length(); i++) {
            if (left.charAt(i) == '.') {
                leftDecimal = left.length() - i -1;
                continue;
            }
            leftInts.add(left.charAt(i) - '0');
        }

        int shift10 = 0;
        //右边操作数的小数点位数
        int rightDecimal = 0;
        for (int i=right.length()-1; i>= 0; i--) {
            if (right.charAt(i) == '.') {
                rightDecimal = right.length() - i -1;
                continue;
            }

            int curRight = right.charAt(i) - '0';
            for (int j=leftInts.size()-1,resultIndex = 0; j >=0; j--, resultIndex++) {
                int curLeft = leftInts.get(j);

                int curMulti = curLeft * curRight;
                int curIndex = shift10 + resultIndex;
                if (curIndex < result.size()) {
                    result.set(curIndex, result.get(curIndex) + curMulti);
                } else {
                    result.add(curMulti);
                }
            }

            //格式化,进位处理
            for (int k = 0; k < result.size()-1; k++) {
                int curValue = result.get(k);
                if(curValue < 10) {
                    continue;
                }
                result.set(k, curValue%10);
                result.set(k+1, result.get(k+1) + curValue/10);
            }

            while (true) {
                int curIndex = result.size()-1;
                int curValue = result.get(curIndex);
                if(curValue < 10) {
                    break;
                }
                result.set(curIndex, curValue%10);
                result.add(curValue/10);
            }

            //下一个十进制位值
            shift10++;
        }


        StringBuffer sb = new StringBuffer();
        for(int i = result.size()-1; i >= 0; i--) {
            sb.append(result.get(i));
        }

        //设置小数点
        int decimal = leftDecimal + rightDecimal;
        if (decimal > 0) {
            if (decimal > sb.length()) {
                int insertZeros = decimal-sb.length();
                while(insertZeros-- > 0) {
                    sb.insert(0, '0');
                }
                sb.insert(0, '.');
            } else {
                sb.insert(sb.length() - decimal, '.');
            }
        }

        //去零
        return trim(sb.toString());
    }

    public static void main(String[] args) {
	    // write your code here
        Scanner cin = new Scanner(System.in);
        while (true) {
            String input = cin.nextLine();
            if (input == null) break;
            String [] values = input.split("\\s+");

            String value = values[0];
            int n = Integer.valueOf(values[1]);
            if (n == 0) {
                System.out.println("1");
                continue;
            }

            String trimValue = trim(value);
            String result = trimValue;
            for(int i=0; i<n-1; i++) {
                result = multi(result, trimValue);
            }
            System.out.println(result);
        }
    }
}
