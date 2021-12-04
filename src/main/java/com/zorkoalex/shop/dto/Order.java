package com.zorkoalex.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zorkoalex.shop.orders.Delivery;
import com.zorkoalex.shop.orders.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "Order")
@Validated
public class Order {
    @NotNull
    @Schema(description = "user info", required = true)
    @JsonProperty("user")
    private User user;

    @NotNull
    @Schema(description = "Payment", required = true)
    @JsonProperty("payment")
    private Payment payment;

    @NotNull
    @Schema(description = "do you need delivery?", required = true)
    @JsonProperty("delivery")
    private Delivery delivery;

    @NotNull
    @Schema(description = "Delivery address", required = true)
    @JsonProperty("deliveryAddress")
    private String deliveryAddress;

    @NotNull
    @Schema(description = "Delivery time", required = true)
    @JsonProperty("deliveryTime")
    private LocalDateTime deliveryTime;

    @NotNull
    @Schema(description = "Order status", required = true)
    @JsonProperty("orderStatus")
    private OrderStatus orderStatus;

    @NotNull
    @Schema(description = "purchases", required = true)
    @JsonProperty("purchases")
    private List<Purchase> purchases;
}
