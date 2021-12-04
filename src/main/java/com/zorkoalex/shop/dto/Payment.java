package com.zorkoalex.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zorkoalex.shop.orders.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Schema (description = "info about payment")
@Validated
public class Payment {
    @NotNull
    @Schema(description = "amount of payment", required = true)
    @JsonProperty("amount")
    private Double amount;

    @NotNull
    @Schema(description = "Delivery address", required = true)
    @JsonProperty("paymentDate")
    private LocalDateTime paymentDate;

    @NotNull
    @Schema(description = "status", required = true)
    @JsonProperty("status")
    private PaymentStatus status;
}
