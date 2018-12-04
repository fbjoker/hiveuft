package com.alex.ct.produer;

import com.alex.ct.produer.bean.LocalFileProducer;
import com.alex.ct.produer.io.LocalFileIn;
import com.alex.ct.produer.io.LocalFileOut;

import java.io.IOException;

public class BootStrap {
    public static void main(String[] args) throws IOException {

//        DataIn in = new LocalFileIn();
//        DataOut out= new LocalFileOut();


        LocalFileProducer producer = new LocalFileProducer();
        producer.setIn(new LocalFileIn("D:\\contact.log"));
        producer.setOut(new LocalFileOut("D:\\ttt.log"));

        producer.produce();


        producer.close();



    }

}
