# Apache Kafka Learning Plan

## Goal

Learn Apache Kafka deeply enough to understand how producers, consumers, topics, partitions, pub-sub/event streaming, delivery guarantees, and schema management work under the hood.

The goal is not only to use Kafka with Spring Boot, but to understand what Spring Boot hides.

\---

## Total Estimated Time

**Total: 55–70 hours**

This is enough for a serious first pass.  
It will not make me a Kafka expert, but it should give me strong practical understanding.

\---

## Phase 1 — Kafka Fundamentals

**Estimated time: 6–8 hours**

### Main resource

* Confluent Introduction to Apache Kafka course

### Topics to learn

* What Kafka is
* Event streaming vs traditional pub-sub
* Topics
* Partitions
* Offsets
* Brokers
* Producers
* Consumers
* Consumer groups
* Basic replication
* Retention

### Goal

By the end of this phase, I should be able to explain Kafka in simple words:

> Kafka is a distributed, durable event log where producers write events to topics and consumers read them at their own pace.

### Done when I can answer

* What is a topic?
* What is a partition?
* Why does Kafka use offsets?
* What is a consumer group?
* Why can Kafka replay old events?
* How is Kafka different from simple pub-sub?

\---

## Phase 2 — Raw Kafka Java Producer and Consumer

**Estimated time: 8–10 hours**

### Main resource

* Apache Kafka Java client documentation
* Kafka: The Definitive Guide, chapters about producers and consumers

### What to build

A small Java project without Spring Boot.

### Features

* Start Kafka locally with Docker Compose
* Create one topic
* Create one producer
* Create one consumer
* Send JSON messages
* Read messages from a consumer
* Use consumer groups
* Try manual offset commits

### Experiments

* Run one consumer and send messages
* Run two consumers in the same group
* Run more consumers than partitions
* Kill one consumer and observe rebalance
* Change event keys and observe partition routing
* Disable auto-commit and commit manually
* Throw an exception before committing and observe reprocessing

### Goal

Understand what actually happens before Spring Boot abstracts it away.

### Done when I can answer

* What does the producer actually send?
* How does Kafka decide which partition receives a message?
* What does the consumer poll loop do?
* What happens if a consumer crashes?
* What is a rebalance?
* What happens if an offset is not committed?

\---

## Phase 3 — Kafka Internals

**Estimated time: 8–10 hours**

### Main resources

* Kafka: The Definitive Guide
* Apache Kafka official documentation
* Confluent architecture/internal explanations

### Topics to learn

* Log segments
* Partition leaders
* Follower replicas
* In-sync replicas
* Leader election
* Retention
* Log compaction
* Producer batching
* Acknowledgements
* Consumer group coordinator
* Rebalancing

### Goal

Understand Kafka as a distributed log, not just as a message queue.

### Done when I can answer

* Why is a partition ordered but a topic is not globally ordered?
* What does replication factor mean?
* What is an ISR?
* What does `acks=all` mean?
* What happens when a broker dies?
* What is log retention?
* What is log compaction?
* Why can one partition become “hot”?

\---

## Phase 4 — Delivery Semantics and Reliability

**Estimated time: 8–10 hours**

### Main resources

* Kafka: The Definitive Guide, reliable delivery / exactly-once chapters
* Confluent delivery semantics documentation

### Topics to learn

* At-most-once processing
* At-least-once processing
* Exactly-once semantics
* Idempotent producers
* Idempotent consumers
* Producer retries
* Consumer offset commits
* Manual commits
* Duplicate events
* Dead-letter topics
* Retry topics
* Kafka transactions

### Experiments

* Consumer crashes before committing offset
* Consumer crashes after database write but before offset commit
* Producer retries after timeout
* Duplicate event processing
* Manual retry topic
* Dead-letter topic for failed messages

### Goal

Understand the real reliability problems in Kafka systems.

### Done when I can answer

* Why does at-least-once processing create duplicates?
* Why is exactly-once not magic?
* What does idempotency mean?
* What does `enable.idempotence=true` do?
* When should I manually commit offsets?
* What is a dead-letter topic?
* What should happen when message processing fails?

\---

## Phase 5 — Schema Management

**Estimated time: 6–8 hours**

### Main resources

* Confluent Schema Registry course
* Confluent Schema Registry documentation

### Topics to learn

* Why schemas matter
* JSON vs Avro vs Protobuf
* Schema Registry
* Schema IDs
* Schema versions
* Compatibility rules
* Backward compatibility
* Forward compatibility
* Breaking changes
* Event versioning

### Experiments

* Send plain JSON event
* Add a new optional field
* Remove a field
* Rename a field
* Change a field type
* Use Avro with Schema Registry
* Test compatible and incompatible schema changes

### Goal

Understand how producers and consumers can evolve safely over time.

### Done when I can answer

* Why is plain JSON risky in Kafka?
* What problem does Schema Registry solve?
* What is backward compatibility?
* What is a breaking schema change?
* Why is renaming a field dangerous?
* When should I version events?

\---

## Phase 6 — Spring Boot with Kafka

**Estimated time: 8–10 hours**

### Main resource

* Confluent Spring Boot with Kafka course / codelab
* Spring Kafka documentation when needed

### Topics to learn

* `KafkaTemplate`
* `@KafkaListener`
* Consumer groups in Spring
* Producer configuration
* Consumer configuration
* Error handling
* Retry handling
* Dead-letter topics
* JSON/Avro serialization
* Testing Kafka with Testcontainers

### What to build

A small Spring Boot Kafka application.

### Features

* REST endpoint that publishes an event
* Kafka listener that consumes the event
* Manual or controlled offset commit strategy
* Error handling
* Retry topic
* Dead-letter topic
* Integration test with Kafka container

### Goal

Use Kafka professionally with Spring Boot while still understanding the underlying Kafka behavior.

### Done when I can answer

* What does `KafkaTemplate` hide?
* What does `@KafkaListener` hide?
* How do I configure consumer groups?
* How do I handle failed messages?
* How do I test Kafka code?
* How do I avoid blindly trusting Spring abstractions?

\---

## Phase 7 — Final Project

**Estimated time: 12–16 hours**

### Project idea

Build a small event-driven order processing system.

### Services

* `order-service`
* `payment-service`
* `inventory-service`
* `notification-service`

### Example events

* `OrderCreated`
* `PaymentAuthorized`
* `PaymentFailed`
* `InventoryReserved`
* `InventoryRejected`
* `OrderConfirmed`
* `OrderCancelled`
* `NotificationRequested`

### Example topics

* `orders.events`
* `payments.events`
* `inventory.events`
* `notifications.commands`
* `orders.dlt`

### Concepts to apply

* Event keys
* Partitioning by `orderId`
* Consumer groups
* Retry topics
* Dead-letter topics
* Idempotent consumers
* Manual offset commits
* Schema evolution
* Avro or JSON Schema
* Basic observability/logging

### Goal

Create a realistic Kafka project that proves I understand Kafka beyond tutorials.

### Done when the project has

* Multiple services communicating through Kafka
* At least 3 Kafka topics
* Events keyed by aggregate ID, for example `orderId`
* One retry mechanism
* One dead-letter topic
* One idempotent consumer
* One schema evolution example
* Clear README explaining the architecture

\---

## Suggested Weekly Schedule

### Option A — Slow pace

**5 hours/week**  
Estimated duration: **11–14 weeks**

Good if I want to learn without pressure.

### Option B — Medium pace

**8 hours/week**  
Estimated duration: **7–9 weeks**

Good balance between depth and consistency.

### Option C — Intensive pace

**12 hours/week**  
Estimated duration: **5–6 weeks**

Good if I want to focus seriously for a short period.

\---

## Recommended Order

1. Kafka Fundamentals
2. Raw Java Producer and Consumer
3. Kafka Internals
4. Delivery Semantics and Reliability
5. Schema Management
6. Spring Boot with Kafka
7. Final Project

\---

## Important Rule

Do not start with Spring Boot first.

Spring Boot is useful, but it hides Kafka's core mechanisms.  
The correct path is:

> Kafka basics → raw Java Kafka client → internals and reliability → schema management → Spring Boot → real project

\---

## Success Criteria

At the end of this plan, I should be able to explain and implement:

* A Kafka producer
* A Kafka consumer
* A consumer group
* Partition-based ordering
* Offset commits
* Retry and dead-letter handling
* At-least-once processing
* Duplicate-safe/idempotent processing
* Basic Schema Registry usage
* Spring Boot Kafka integration
* A small event-driven architecture

\---

## Notes

Kafka should be learned by breaking things, not only by watching courses.

For every concept, I should ask:

* What happens if the producer fails?
* What happens if the broker fails?
* What happens if the consumer fails?
* What happens if the message is invalid?
* What happens if the same event is processed twice?

