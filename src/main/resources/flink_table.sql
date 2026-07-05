CREATE TABLE SensorTemperature (
  -- declare the schema of the table
  `sensorId` INT,
  `message` STRING) WITH (
  'connector' = 'kafka',
  'topic' = 'sensor-temperature',
  'scan.startup.mode' = 'earliest-offset',
  'properties.bootstrap.servers' = 'localhost:9092',
  'format' = 'json'   -- declare a format for this system
)