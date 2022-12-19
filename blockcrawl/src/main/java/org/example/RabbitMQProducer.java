package org.example;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class RabbitMQProducer {
    String queueName;
    ConnectionFactory factory;
    Channel channel;

    RabbitMQProducer() {
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


    void produce() {
        try {
            while (true) {
                this.channel.queueDeclare(this.queueName, false, false, false, null);
                String message = "Hello World!" + (int) (Math.random() * 100);
                this.channel.basicPublish("", this.queueName, null, message.getBytes());
                System.out.println(" [x] Set '" + message + "'");
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void consume() {

    }
}