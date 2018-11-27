package com.alex.test.demo1.demo1c;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.Future;

public class TestProducerc {

    public static void main(String[] args) {

        Properties prop = new Properties();

        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.ACKS_CONFIG,"1");
        prop.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.alex.test.demo1.demo1c.PatitionTestc");




        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        ProducerRecord<String, String> recoder = new ProducerRecord<String, String>("demo1", "testcpatition");
        Future<RecordMetadata> send = producer.send(recoder, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                System.out.println(metadata.partition());
                System.out.println(metadata.offset());
            }
        });


        producer.close();
    }
}
