package com.alex.ct.io;

import com.alex.ct.bean.DataOut;

import java.io.*;

public class LocalFileOut implements DataOut {
     private PrintWriter write;

    public LocalFileOut() {
    }

    public LocalFileOut(String path) {

        setPath(path);
    }

    public void setPath(String path) {


        try {
            write= new PrintWriter(new OutputStreamWriter(new FileOutputStream(path),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void write(Object data) throws IOException {

        write(data.toString());

    }

    public void write(String data) throws IOException {
        write.println(data);
        write.flush();


    }

    public void close() throws IOException {

        if(write!=null){
            write.close();
        }

    }
}
