package com.exemplo.producer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    private final MessageProducer producer;

    public ProducerController(MessageProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/send")
    public String send() {
        producer.send("Evento FANOUT");
        return "Mensagem enviada";
    }
}
