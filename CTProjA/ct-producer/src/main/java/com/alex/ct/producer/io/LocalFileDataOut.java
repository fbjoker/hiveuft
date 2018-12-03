package com.alex.ct.producer.io;

import com.alex.ct.common.bean.DataOut;

import java.io.*;

//本地文件输出
public class LocalFileDataOut implements DataOut {

    private  PrintWriter writer;
    private  int count=0;


    public  LocalFileDataOut(String path){

        setPath( path);
    }
    public void setPath(String path) {
        try {
            writer=new PrintWriter( new OutputStreamWriter(new FileOutputStream(path),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void write(Object data) throws Exception {
        write(data.toString());
    }

    public void write(String data) throws Exception {


        writer.println(data);
      //  writer.write(data);
        if(count>5000){

        writer.flush();
        count=0;
        }



    }

    public void close() throws IOException {
        writer.close();

    }
}
