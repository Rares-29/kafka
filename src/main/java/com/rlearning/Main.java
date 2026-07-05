package com.rlearning;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rlearning.consumer.Consumer;
import com.rlearning.flink.TemperatureAverageJob;
import com.rlearning.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args[0].equals("P"))
        {
            Producer.sendMessage();
        } else if (args[0].equals("C")) {
            Consumer.consumeMessages();
        } else {
            TemperatureAverageJob.computeAverages();
        }
    }

}