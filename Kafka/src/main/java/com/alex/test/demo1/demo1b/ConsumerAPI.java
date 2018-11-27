package com.alex.test.demo1.demo1b;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.cluster.BrokerEndPoint;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ConsumerAPI {


    @SuppressWarnings("all")
    public static void main(String[] args) throws UnsupportedEncodingException {
        BrokerEndPoint leader=null;
        SimpleConsumer simpleConsumer = new SimpleConsumer("hadoop102",9092,500,10*1024,"id01");

        TopicMetadataRequest topicMetadataRequest = new TopicMetadataRequest(Arrays.asList("demo1"));

        TopicMetadataResponse response = simpleConsumer.send(topicMetadataRequest);

        for (TopicMetadata topicMeta : response.topicsMetadata()) {

            if("demo1".equals(topicMeta.topic())){
                for (PartitionMetadata partitiontopic : topicMeta.partitionsMetadata()) {
                    if (partitiontopic.partitionId()==1){

                        leader = partitiontopic.leader();
                    }

                }

            }

        }


        SimpleConsumer consumer = new SimpleConsumer(leader.host(), leader.port(), 500, 10 * 1024, "id2");

        FetchRequest fetch = new FetchRequestBuilder().addFetch("demo1",1,0,10*1024).build();

        FetchResponse resp1 = consumer.fetch(fetch);
        ByteBufferMessageSet ms = resp1.messageSet("demo1", 1);

        for (MessageAndOffset m : ms) {
            ByteBuffer bb = m.message().payload();
            byte[] bytes= new byte[bb.limit()];
             bb.get(bytes);
            System.out.println(new String(bytes,"UTF-8"));



        }


    }
}
