package com.exemplo.consumerA;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerAConfig {
    @Bean
    public Queue queueA() {
        return new Queue("queue-A");
    }

    @Bean
    public Binding bindingA(FanoutExchange exchange, Queue queueA) {
        return BindingBuilder.bind(queueA).to(exchange);
    }
}
