package org.example;

public class Main {
    public static void main(String[] args) {
        RabbitMQConsumer consumer = new RabbitMQConsumer();
        consumer.consume();
    }
}