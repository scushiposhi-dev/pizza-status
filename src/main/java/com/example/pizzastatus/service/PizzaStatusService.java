package com.example.pizzastatus.service;

import com.example.orderpizza.message.PizzaMakerStatus;
import com.example.pizzastatus.domain.PizzaStatus;
import com.example.pizzastatus.repositoy.PizzaStatusRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class PizzaStatusService {

    private final PizzaStatusRepository pizzaStatusRepository;

    @Transactional
    public void process(PizzaMakerStatus pizzaMakerStatus) {
        pizzaStatusRepository.save(PizzaStatus.builder()
                        .status(pizzaMakerStatus.getPizzaStatusEnum())
                        .orderId(pizzaMakerStatus.getOrderId()).build())
                .log()
                .subscribe();
    }
}
