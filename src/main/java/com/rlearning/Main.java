package com.rlearning;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        RoomTemperature roomTemperature = new RoomTemperature(32, "kitchen", Instant.now());
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        String json = mapper.writeValueAsString(roomTemperature);
        ProducerRecord<Integer,String> record = new ProducerRecord<Integer, String>("sensor-temperature", 1, json);

        Properties properties = loadProperties();
        KafkaProducer<Integer, String> producer = new KafkaProducer<>(properties);
        for (int i = 0; i < 10; i++)
            producer.send(record);
        producer.flush();
        producer.close();
    }

    public static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = Main.class
                .getClassLoader()
                .getResourceAsStream("kafka.properties")) {

            if (input == null) {
                throw new RuntimeException("kafka.properties not found in src/main/resources");
            }

            properties.load(input);
            return properties;

        } catch (IOException e) {
            throw new RuntimeException("Failed to load kafka.properties", e);
        }
    }
}