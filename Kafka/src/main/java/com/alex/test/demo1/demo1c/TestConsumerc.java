package com.alex.test.demo1.demo1c;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class TestConsumerc {
    public static void main(String[] args) {

        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers","hadoop102:9092");
        prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // value的序列化类
        prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        prop.setProperty("group.id","alex");
        prop.put("enable.auto.commit", "true");
        prop.put("auto.commit.interval.ms", "1000");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);



        consumer.subscribe(Arrays.asList("demo1"));

        while (true){
        ConsumerRecords<String, String> poll = consumer.poll(200);
            for (ConsumerRecord<String, String> record : poll) {
                System.out.println(record.value());

            }



        }


    }
}
