package com.zorkoalex.shop.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zorkoalex.shop.dto.user.User;
import com.zorkoalex.shop.database.Delivery;
import com.zorkoalex.shop.database.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

@Data
@Schema(description = "Order")
@Validated
public class Order {
    @Null
    @Schema(description = "Id", required = true)
    @JsonProperty("id")
    private Long id;

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
    private String deliveryTime;

    @Null
    @Schema(description = "Order status", required = true)
    @JsonProperty("orderStatus")
    private OrderStatus orderStatus;

    @Null
    @Schema(description = "purchases", required = true)
    @JsonProperty("purchases")
    private List<Purchase> purchases;
}
