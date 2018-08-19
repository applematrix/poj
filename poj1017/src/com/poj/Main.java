package com.poj;

import java.util.Scanner;

public class Main {

    public static int calcPackets(int[] products) {
        int packets = 0;
        //products with size 6 alway use a packet
        int packet6 = products[6];

        //products with size 5 alway use a packet but with 11 1*1 space remaind
        int packet5 = products[5];
        packets = packet5 + packet6;

        int size1RemaindInPacket5 = products[5]*11;

        packets += products[4];
        int size2RemaindInPacket4 = products[4]*5;

        //parcel product size 3
        int size3UsedPackets = products[3] / 4;
        packets += size3UsedPackets;

        int size3RemaindProducts = products[3] % 4;
        int size2RemaindInPacket3 = 0;
        int size1RemaindInPacket3 = 0;
        if (size3RemaindProducts > 0) {
            //use a new packet to parcel the remaind product of size 3*3
            packets += 1;
            if (size3RemaindProducts == 3) {
                size2RemaindInPacket3 = 1;
                size1RemaindInPacket3 = 5;
            } else if (size3RemaindProducts == 2) {
                size2RemaindInPacket3 = 3;
                size1RemaindInPacket3 = 6;
            } else if (size3RemaindProducts == 1) {
                size2RemaindInPacket3 = 5;
                size1RemaindInPacket3 = 7;
            }
        }

        //parcel product size 2;
        int remaindSize2 = size2RemaindInPacket3 + size2RemaindInPacket4;
        //parcel the product with size 2*2 in the remaind space;
        if (products[2] > remaindSize2) {
            products[2] -= remaindSize2;
            remaindSize2 = 0;
        } else {
            remaindSize2 -= products[2];
            products[2] = 0;
        }

        //use new packets to parcel the product size 2*2
        int remaindSize1InPacket2 = 0;
        packets += products[2]/9;
        products[2] %= 9;
        if (products[2] > 0) {
            //use a new packet to parcel the remaind product of size 3*3
            packets += 1;
            remaindSize1InPacket2 = (9 - (products[2]%9))*4;
        }

        int allRemaindSize1 = remaindSize1InPacket2
                + remaindSize2*4
                + size1RemaindInPacket3
                + size1RemaindInPacket5;

        if (products[1] <= allRemaindSize1) {
            return packets;
        } else {
            int noParceledSize1 = products[1] - allRemaindSize1;
            packets += (noParceledSize1/36);
            if (noParceledSize1 % 36 > 0) {
                packets++;
            }
        }
        return packets;
    }

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        while (true) {
            int[] packets = new int[6+1];
            boolean end = true;
            for (int i = 1 ; i <= 6 ; i++) {
                packets[i] = cin.nextInt();
                if (packets[i] != 0) {
                    end = false;
                }
            }
            if (end) {
                break;
            }

            System.out.println(calcPackets(packets));
        }
    }
}
