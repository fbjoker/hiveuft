package com.alex.test.demo1.demo1b;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class TestProducerb {


    public static void main(String[] args) {


        Properties prop = new Properties();

        prop.setProperty("bootstrap.servers","hadoop102:9092");
        prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.setProperty("acks","1");
       // prop.setProperty("partitioner.class","com.alex.test.demo1.TestPartiton");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        ProducerRecord<String, String> record = new ProducerRecord<String, String>("demo1", "nome");
        producer.send(record);
    }
}
