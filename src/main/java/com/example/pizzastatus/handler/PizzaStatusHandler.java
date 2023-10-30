package com.example.pizzastatus.handler;

import com.example.orderpizza.message.PizzaMakerStatus;
import com.example.pizzastatus.service.PizzaStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PizzaStatusHandler {

    private final PizzaStatusService pizzaStatusService;
    @KafkaListener(
            id = "pizzaConsumerClient",
            groupId = "pizza.status.consumer",
            topics = {"pizza.status"},
            containerFactory = "concurrentKafkaListenerContainerFactory")
    public void listen(PizzaMakerStatus pizzaMakerStatus) {
        log.info("Received message:{}", pizzaMakerStatus);
        pizzaStatusService.process(pizzaMakerStatus);
    }
}
