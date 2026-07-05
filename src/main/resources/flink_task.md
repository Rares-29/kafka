Goal: Calculate the average temperature per room and output it to the console

Example input from your producer:
{"sensorId":1,"temperature":32,"room":"kitchen","timestamp":"..."}
{"sensorId":2,"temperature":28,"room":"kitchen","timestamp":"..."}
{"sensorId":3,"temperature":20,"room":"bedroom","timestamp":"..."}

Expected Flink output:
kitchen average = 30
bedroom average = 20
