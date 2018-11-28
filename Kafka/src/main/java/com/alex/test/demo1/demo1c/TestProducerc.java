package com.alex.test.demo1.demo1c;

import org.apache.kafka.clients.producer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

public class TestProducerc {

    public static void main(String[] args) {

        Properties prop = new Properties();

        List<String> intercept = new ArrayList<String>();
        intercept.add("com.alex.test.demo1.demo2.Itercepter1");
        intercept.add("com.alex.test.demo1.demo2.intercepter2");

        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.ACKS_CONFIG,"1");
        prop.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,"com.alex.test.demo1.demo1c.PatitionTestc");
        prop.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,intercept);




        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        ProducerRecord<String, String> recoder = new ProducerRecord<String, String>("demo1", "testintercepter2");
        Future<RecordMetadata> send = producer.send(recoder, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                System.out.println(metadata.partition());
                System.out.println(metadata.offset());
                System.out.println(metadata.serializedValueSize());
            }
        });


        producer.close();
    }
}
