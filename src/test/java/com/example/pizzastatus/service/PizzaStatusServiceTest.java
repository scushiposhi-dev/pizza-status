package com.example.pizzastatus.service;

import com.example.orderpizza.message.PizzaMakerStatus;
import com.example.orderpizza.message.PizzaStatusEnum;
import com.example.pizzastatus.repositoy.PizzaStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PizzaStatusServiceTest {

    PizzaStatusRepository pizzaStatusRepository;
    PizzaStatusService pizzaStatusService;
    PizzaMakerStatus pizzaMakerStatus;

    @BeforeEach
    void setUp() {
        pizzaStatusRepository = mock(PizzaStatusRepository.class);
        pizzaStatusService = new PizzaStatusService((pizzaStatusRepository));
        pizzaMakerStatus = PizzaMakerStatus.builder()
                .pizzaStatusEnum(PizzaStatusEnum.BAKING)
                .orderId(randomUUID()).build();
    }

    @Test
    void process() {
        when(pizzaStatusRepository.save(any())).thenReturn(Mono.empty());
        assertDoesNotThrow(()->pizzaStatusService.process(pizzaMakerStatus));
    }
}