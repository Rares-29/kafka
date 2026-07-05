package com.rlearning.flink;


import org.apache.flink.table.api.*;
import org.apache.flink.table.catalog.Catalog;
import org.apache.flink.table.catalog.GenericInMemoryCatalog;

public class TemperatureAverageJob {
    public static void computeAverages() {
        EnvironmentSettings settings = EnvironmentSettings
                .newInstance()
                .inStreamingMode()
                //.inBatchMode()
                .build();

        TableEnvironment tableEnv = TableEnvironment.create(settings);
        Catalog catalog = new GenericInMemoryCatalog("mem-cat");
        tableEnv.registerCatalog("mem-cat", catalog);
        tableEnv.executeSql("CREATE DATABASE mydb WITH (...)");
    }
}
