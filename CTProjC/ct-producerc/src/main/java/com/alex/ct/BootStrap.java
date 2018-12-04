package com.alex.ct;

import com.alex.ct.bean.LocalFileProducer;
import com.alex.ct.io.LocalFileIn;
import com.alex.ct.io.LocalFileOut;

import java.io.IOException;

public class BootStrap {

    public static void main(String[] args) throws IOException {

        LocalFileProducer producer = new LocalFileProducer();
        producer.setIn( new LocalFileIn(""));
        producer.setOut(new LocalFileOut(""));
        producer.produce();


        producer.close();




    }
}
