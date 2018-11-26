package com.alex.test.demo1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class TestProducer {

    public static void main(String[] args) {

        Properties prop = new Properties();

        prop.setProperty("bootstrap.servers","hadoop102:9092");
        prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.setProperty("acks","1");


        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        String topic="demo1";
        String v="============================>";
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,v);

        producer.send(record);

        // 关闭生产者
        producer.close();
    }















}
