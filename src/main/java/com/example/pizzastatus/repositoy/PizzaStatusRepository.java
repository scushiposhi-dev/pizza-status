package com.example.pizzastatus.repositoy;

import com.example.pizzastatus.domain.PizzaStatus;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface PizzaStatusRepository extends ReactiveMongoRepository<PizzaStatus, String> {

    @Tailable
    Flux<PizzaStatus> findByOrderId(UUID uuid);
}
