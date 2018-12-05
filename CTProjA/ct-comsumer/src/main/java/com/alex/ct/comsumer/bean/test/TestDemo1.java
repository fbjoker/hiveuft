package com.alex.ct.comsumer.bean.test;

import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

public class TestDemo1 {
    public static void main(String[] args) {

        byte[][] bs = new byte[3][];
        // 0|,1|,2|,3|,4|
        // (-∞, 0|), [0|,1|), [1| +∞)
        List<byte[]> bsList = new ArrayList<byte[]>();
        String splitkey = 2 + "|";
        bsList.add(Bytes.toBytes(splitkey));
        byte[][] bytes = bsList.toArray(bs);

        System.out.println(bsList);
        for (byte[] bytes1 : bsList) {
            for (byte b : bytes1) {
                System.out.println(b);

            }
//            System.out.println(bytes1);
        }
        System.out.println(bytes);
        for (byte[] aByte : bytes) {
            System.out.println(aByte);

        }
        byte[] bytes1 = "2".getBytes();
        byte[] bytes2 = Bytes.toBytes("2");

        System.out.println(bytes1);
        System.out.println(bytes2);
        System.out.println("===================================");

        int i = "1314".hashCode();
        int i1 = "1314".hashCode();
        int j = "20181111".hashCode();
        System.out.println(i);
        System.out.println(i^j);
        System.out.println(j);


    }
}
