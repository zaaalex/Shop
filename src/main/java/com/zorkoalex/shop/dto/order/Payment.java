package com.zorkoalex.shop.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zorkoalex.shop.database.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Schema (description = "info about payment")
@Validated
public class Payment {
    @Null
    @Schema(description = "id", required = true)
    @JsonProperty("id")
    private Long id;

    @Null
    @Schema(description = "amount of payment", required = true)
    @JsonProperty("amount")
    private Double amount;

    @NotNull
    @Schema(description = "status", required = true)
    @JsonProperty("status")
    private PaymentStatus status;
}
