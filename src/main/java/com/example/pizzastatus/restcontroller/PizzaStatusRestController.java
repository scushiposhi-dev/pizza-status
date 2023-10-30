package com.example.pizzastatus.restcontroller;

import com.example.pizzastatus.domain.PizzaStatus;
import com.example.pizzastatus.repositoy.PizzaStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping(path="/v1/pizzastatus")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class PizzaStatusRestController {

    private final PizzaStatusRepository pizzaStatusRepository;

    @GetMapping(path="/{orderId}/status",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PizzaStatus> streamStatusByOrderId(@PathVariable UUID orderId) {
        return pizzaStatusRepository
                .findByOrderId(orderId)
                .subscribeOn(Schedulers.boundedElastic()).delayElements(Duration.ofSeconds(5))
                .log()
                .doOnError(throwable -> log.error("[ON_ERROR={}]", throwable.getMessage()));
    }

}
