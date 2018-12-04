package com.alex.ct.io;

import com.alex.ct.bean.Data;
import com.alex.ct.bean.DataIn;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LocalFileIn implements DataIn {

    private BufferedReader read;


    public LocalFileIn() {
    }

    public LocalFileIn(String paht) {
        setPath(paht);
    }

    public void setPath(String path) {


        try {
            read = new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public Object read() throws IOException {


        return null;
    }

    public <T extends Data> List<T> read(Class<T> clazz) throws IOException {

        List<T> list = new ArrayList<T>();
        try {
            String line;

            while ((line = read.readLine()) != null) {

                T t = null;

                t = clazz.newInstance();

                t.setVal(line);
                list.add(t);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return list;
    }

    public void close() throws IOException {


        if (read != null) {
            read.close();
        }
    }
}
