package com.zorkoalex.shop.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Info about purchase")
@Validated
public class Purchase {
    @NotNull
    @Schema(description = "cakeId", required = true)
    private Long cakeId;

    @NotNull
    @Schema(description = "count cake", required = true)
    private Integer count;
}
