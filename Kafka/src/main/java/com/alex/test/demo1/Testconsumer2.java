package com.alex.test.demo1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class Testconsumer2 {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers","hadoop102:9092");
        prop.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        prop.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        prop.setProperty("group.id","test1");
        prop.setProperty("enable.auto.commit","true");
        prop.put("auto.commit.interval.ms", "1000");


        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(prop);
        consumer.subscribe(Arrays.asList("demo1"));

        while (true){
            ConsumerRecords<String, String> records = consumer.poll(500);

            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
            }
        }


    }
}
