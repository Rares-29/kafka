package com.rlearning.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import static com.rlearning.utils.Properties.loadProperties;

public class Consumer {


    public static void consumeMessages() {

        Properties properties = loadProperties();
        KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(List.of("sensor-temperature"));
        while (true) {
        ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofMillis(10));
        for (ConsumerRecord<Integer, String> record : records) {
            System.out.println("Key: " + record.key() + "Value: " + record.value());
        }
        }
    }
}
