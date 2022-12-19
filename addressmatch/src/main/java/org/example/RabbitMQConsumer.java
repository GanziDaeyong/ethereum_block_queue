package org.example;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class RabbitMQConsumer {
    String queueName;
    ConnectionFactory factory;
    Channel channel;

    RabbitMQConsumer() {
        this.setConnection();
    }

    private void setConnection() {
        this.factory = new ConnectionFactory();
        this.queueName = "testqueue";
        this.factory.setHost("127.0.0.1");
        this.factory.setPort(5672);
        this.factory.setUsername("admin");
        this.factory.setPassword("1234");
        try {
            Connection connection = this.factory.newConnection();
            this.channel = connection.createChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void consume() {
        try {
            while (true) {
                this.channel.queueDeclare(this.queueName, false, false, false, null);
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String message = new String(delivery.getBody(), "UTF-8");
                    System.out.println(" [x] Received '" + message + "'");
                };
                channel.basicConsume(this.queueName, true, deliverCallback, consumerTag -> { });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}