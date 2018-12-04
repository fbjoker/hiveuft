package com.alex.ct.bean;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class LocalFileProducer implements Producer {
    private  DataIn in;
    private  DataOut out;
    private  boolean flg;


    public void setIn(DataIn in) {
        this.in =in;

    }

    public void setOut(DataOut out) {

        this.out=out;
    }

    public void produce()  {

        try {
            List<Contact> read = in.read(Contact.class);

            int tell1= new Random().nextInt(read.size());
            int tell2=0;
            while (true){
                tell2= new Random().nextInt(read.size());
                if(tell1!=tell2){
                    break;
                }
            }

            Contact call1= read.get(tell1);
            Contact call2= read.get(tell2);


            String starttime= "20180101000000";
            String endtime= "20190101000000";






        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void close() throws IOException {

        if(in!=null){
            in.close();
        }

        if(out!=null){
            out.close();
        }

    }
}
