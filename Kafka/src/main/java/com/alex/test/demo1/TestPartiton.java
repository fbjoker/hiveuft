package com.alex.test.demo1;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class TestPartiton implements Partitioner {
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {

        return 2;
    }

    public void close() {

    }

    public void configure(Map<String, ?> configs) {

    }
}
