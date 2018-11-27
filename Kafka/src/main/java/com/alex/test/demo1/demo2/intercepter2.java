package com.alex.test.demo1.demo2;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class intercepter2 implements ProducerInterceptor<String,String> {
    private int ok=0;
    private  int myGod=0;

    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

        if (exception==null){
            ok++;
        }else {
            myGod++;
        }

    }

    public void close() {
        System.out.println("成功"+ok);
        System.out.println("失败"+myGod);

    }

    public void configure(Map<String, ?> configs) {

    }
}
