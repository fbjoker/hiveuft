package com.alex.test.demo1.demo2;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.apache.kafka.streams.processor.TopologyBuilder;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class Kafkastrem {

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"hadoop102:9092");
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"logFilter");


        TopologyBuilder builder = new TopologyBuilder();
        builder.addSource("s1","demo1");
        builder.addProcessor("p1", new ProcessorSupplier() {
            public Processor get() {
                return new LogProcessor();
            }
        }, "s1");

        builder.addSink("s2","first1","p1");

        KafkaStreams kafkaStreams = new KafkaStreams(builder, properties);

        kafkaStreams.start();


    }
}


class LogProcessor implements Processor<byte[],byte[]>{
    private ProcessorContext context;

    public void init(ProcessorContext processorContext) {
        this.context=processorContext;


    }

    public void process(byte[] key, byte[] v) {
        try {
            String val = new String(v, "utf-8");
            String res = val.replace("#","\t");
            context.forward(key,res.getBytes("UTF-8"));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


    }

    public void punctuate(long l) {

    }

    public void close() {

    }
}