package com.zorkoalex.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Schema(description = "Info about orders")
@Validated
public class Orders {
    @NotNull
    @Schema(description = "order_list", required = true)
    @JsonProperty("order_list")
    private List<Order> orderList;
}
