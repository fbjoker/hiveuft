package com.alex.test.demo1.demo1b;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

public class TestProducerb {


    public static void main(String[] args) {


        Properties prop = new Properties();

        prop.setProperty("bootstrap.servers","hadoop102:9092");
        prop.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.setProperty("acks","1");
        prop.setProperty("partitioner.class","com.alex.test.demo1.demo1b.TestPartitionb");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        ProducerRecord<String, String> record = new ProducerRecord<String, String>("demo1", "00nome4");
        producer.send(record, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                System.out.println(metadata.partition());
                System.out.println(metadata.offset());
            }
        });

        producer.close();
    }
}
