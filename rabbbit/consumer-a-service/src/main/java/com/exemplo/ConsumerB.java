package com.exemplo.consumerB;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerB {
    @RabbitListener(queues = "queue-B")
    public void receive(String message) {
        System.out.println("Consumer B recebeu: " + message);
    }
}
