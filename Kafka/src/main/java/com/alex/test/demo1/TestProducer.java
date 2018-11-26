package com.alex.test.demo1;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class TestProducer {

    public static void main(String[] args) {

        Properties prop = new Properties();

        prop.setProperty("bootstrap.servers","hadoop102:9092");
        prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.setProperty("acks","1");
        prop.setProperty("partitioner.class","com.alex.test.demo1.TestPartiton");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        String topic="demo1";
        String v="2============================>";
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,v);

        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                System.out.println(metadata.partition());

            }
        });

        // 关闭生产者
        producer.close();
    }















}
