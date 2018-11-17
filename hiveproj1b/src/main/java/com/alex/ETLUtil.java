package com.alex;

public class ETLUtil {

    public static String str(String str) {
        String[] split = str.split("\t");
        StringBuilder sb = new StringBuilder();
        if(split.length<9) {
            return  null;
        }
        split[3].replace(" ","");

        for( int i=0;i<split.length;i++){
            if(i<9){
                if(i==split.length-1){
                    sb.append(split[i]);

                }else {
                    sb.append(split[i]).append("\t");
                }

            }else {
                if(i==split.length-1){
                    sb.append(split[i]);

                }else {
                    sb.append(split[i]).append("&");
                }
            }
        }

        return sb.toString();
    }
}
