package com.rlearning.producer;

import com.rlearning.Main;
import com.rlearning.RoomTemperature;
import com.rlearning.utils.JsonMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Properties;
import java.util.Scanner;

import static com.rlearning.utils.Properties.loadProperties;


public class Producer {

    public static void sendMessage() throws Exception {

        Properties properties = loadProperties();
        KafkaProducer<Integer, String> producer = new KafkaProducer<>(properties);
        Scanner scanner = new Scanner(System.in);
        int key;
        System.out.println("Start entering keys and values");
        while((key=scanner.nextInt()) != -1) {
        String value = scanner.next();
        String[] parts = value.split(",");
            RoomTemperature roomTemperature =  new RoomTemperature(key,Integer.parseInt(parts[0]),parts[1], Instant.now());
            ProducerRecord<Integer,String> record = new ProducerRecord<>("sensor-temperature", roomTemperature.sensorId(), JsonMapper.getJsonFromObject(roomTemperature));
            producer.send(record);
        }
        producer.flush();
        producer.close();
    }
}
