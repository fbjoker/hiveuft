package com.alex.ct.produer.io;

import com.alex.ct.bean.DataOut;

import java.io.*;

public class LocalFileOut implements DataOut {
    private PrintWriter out;

    public LocalFileOut() {
    }
    public   LocalFileOut(String path) {
        setPath(path);
    }

    public void setPath(String path) {

        try {
            out=new PrintWriter(new OutputStreamWriter( new FileOutputStream(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void write(Object data) throws IOException {
        write(data.toString());

    }

    public void write(String data) throws IOException {
        out.println(data);
        out.flush();



    }

    public void close() throws IOException {

        if(out!=null){
            out.close();
        }
    }
}
