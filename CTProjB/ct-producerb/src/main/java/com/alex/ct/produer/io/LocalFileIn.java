package com.alex.ct.produer.io;

import com.alex.ct.bean.Data;
import com.alex.ct.bean.DataIn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LocalFileIn implements DataIn {
    private  BufferedReader reader;


    public LocalFileIn() {
    }

    public   LocalFileIn(String path) {
        setPath(path);
    }

    public void setPath(String path) {
        try {
           reader= new BufferedReader( new InputStreamReader( new FileInputStream(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public Object read() throws IOException {
        return null;
    }

    public <T extends Data> List<T> read(Class<T> clazz) throws IOException {
        List<T> list= new ArrayList<T>();
        try {

            String line;

        while ((line=reader.readLine())!=null){
                T t = clazz.newInstance();
                t.setVal(line);
            list.add(t);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void close() throws IOException {

        reader.close();

    }
}
