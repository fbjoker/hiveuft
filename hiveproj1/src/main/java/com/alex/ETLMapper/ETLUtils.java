package com.alex.ETLMapper;

public class ETLUtils {
    public static String etlString(String s) {


        String[] fields = s.split("\t");

        //去掉长度不够9
        if (fields.length < 9) {
            return null;
        }


        //去空格
        fields[3].replace(" ", "");

        StringBuilder sb = new StringBuilder();
        //相关视频加&分割符

        for (int i = 0; i < fields.length; i++) {
            //i<9用空格分割
            if (i < 9) {
                if (i == fields.length - 1) {

                    sb.append(fields[i]);
                } else {

                    sb.append(fields[i]).append("\t");
                }
                //i>9用&分割
            } else {
                if (i == fields.length - 1) {

                    sb.append(fields[i]);
                } else {

                    sb.append(fields[i]).append("&");
                }
            }


        }


        return sb.toString();
    }
}
