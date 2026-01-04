package com.exemplo.consumerB;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerBConfig {
    @Bean
    public Queue queueB() {
        return new Queue("queue-B");
    }

    @Bean
    public Binding bindingB(FanoutExchange exchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(exchange);
    }
}
