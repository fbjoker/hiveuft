package com.alex.test.demo1.demo1c;


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

public class ConsumerAPIc {

    @SuppressWarnings("all")
    public static void main(String[] args) throws UnsupportedEncodingException {
        BrokerEndPoint leader=null;

        SimpleConsumer simpleConsumer = new SimpleConsumer("hadoop102", 9092, 500, 10 * 1024, "id1");

        TopicMetadataRequest req1 = new TopicMetadataRequest(Arrays.asList("demo1"));

        TopicMetadataResponse response1 = simpleConsumer.send(req1);
        for (TopicMetadata topicMetadata : response1.topicsMetadata()) {

            if ("demo1".equals(topicMetadata.topic())) {
                for (PartitionMetadata partitionsMeta : topicMetadata.partitionsMetadata()) {
                    if (1 == partitionsMeta.partitionId()) {

                        leader = partitionsMeta.leader();
                    }


                }

            }


        }

        if (leader==null){

            System.out.println("ok");
            return;
        }


        SimpleConsumer consumer = new SimpleConsumer(leader.host(), 9092, 500, 10 * 1024, "id2");


        FetchRequest req = new FetchRequestBuilder().addFetch("demo1", 0, 0, 10 * 1024).build();

        FetchResponse response = consumer.fetch(req);

        ByteBufferMessageSet mesgs = response.messageSet("demo1", 0);


        for (MessageAndOffset mesg : mesgs) {

            ByteBuffer payload = mesg.message().payload();

            byte[] bytes = new byte[payload.limit()];

            payload.get(bytes);

            System.out.println(new String(bytes, "utf-8"));


        }


    }


}
