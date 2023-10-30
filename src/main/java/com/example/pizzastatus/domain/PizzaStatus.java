package com.example.pizzastatus.domain;

import com.example.orderpizza.message.PizzaStatusEnum;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "pizza_status")
@Builder
public class PizzaStatus {
    @Id
    private String id;
    private UUID orderId;
    private PizzaStatusEnum status;
}
