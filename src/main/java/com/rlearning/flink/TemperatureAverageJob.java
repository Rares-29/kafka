package com.rlearning.flink;


import org.apache.flink.table.api.*;
import org.apache.flink.table.catalog.Catalog;
import org.apache.flink.table.catalog.GenericInMemoryCatalog;

import static org.apache.flink.table.api.Expressions.$;

public class TemperatureAverageJob {
    public static void computeAverages() {
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .inStreamingMode()
                .build();

        TableEnvironment tableEnv = TableEnvironment.create(settings);

        tableEnv.executeSql("""
                CREATE TABLE SensorTemperature (
                  `sensorId` INT,
                  `temperature` INT,
                  `room` STRING) WITH (
                  'connector' = 'kafka',
                  'topic' = 'sensor-temperature',
                  'properties.bootstrap.servers' = 'localhost:9092',
                  'properties.group.id' = 'testGroup',
                  'scan.startup.mode' = 'earliest-offset',
                  'format' = 'json',
                  'json.ignore-parse-errors' = 'true'
                )""");
        Table table = tableEnv.from("SensorTemperature");
        Table result = table.groupBy($("room"))
                .select($("room"), $("temperature").avg().as("avg_temperature"));
        result.execute().print();
    }
}
