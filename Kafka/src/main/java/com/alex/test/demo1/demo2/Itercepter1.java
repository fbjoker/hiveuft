package com.alex.test.demo1.demo2;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class Itercepter1 implements ProducerInterceptor<String,String> {
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String s = record.value();

        String v= System.currentTimeMillis()+"#"+s;

        ProducerRecord<String, String> pr = new ProducerRecord<String, String>("demo1", v);

        return pr;
    }

    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

    }

    public void close() {

    }

    public void configure(Map<String, ?> configs) {

    }
}
