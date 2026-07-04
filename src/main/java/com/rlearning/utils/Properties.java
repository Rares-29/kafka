package com.rlearning.utils;

import com.rlearning.Main;

import java.io.IOException;
import java.io.InputStream;

public class Properties {

    public static java.util.Properties loadProperties() {
        java.util.Properties properties = new java.util.Properties();

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
