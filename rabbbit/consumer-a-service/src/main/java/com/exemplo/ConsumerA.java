package com.exemplo.consumerA;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerA {
    @RabbitListener(queues = "queue-A")
    public void receive(String message) {
        System.out.println("Consumer A recebeu: " + message);
    }
}