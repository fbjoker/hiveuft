package com.alex.ct.producer.io;

import com.alex.ct.common.bean.Data;
import com.alex.ct.common.bean.DataIn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//本地文件输入
public class LocalFileDataIn implements DataIn {

    private BufferedReader reader = null;

    public LocalFileDataIn(String path) {

        setPath(path);
    }

    public void setPath(String path) {


        try {
            //文件流的操作
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Object read() throws IOException {
        return null;
    }

    //方法的泛型用法
    public <T extends Data> List<T> read(Class<T> clazz) throws IOException {
        List<T> ts = new ArrayList<T>();
        try {
        String line =null;

        while ( (line=reader.readLine())!=null){

                T t = (T)clazz.newInstance();

            t.setvalue(line);

            ts.add(t);

        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ts;

    }


    public void close() throws IOException {

    if( reader!=null){
        reader.close();
    }

    }

}


