package com.zorkoalex.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zorkoalex.shop.users.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Schema(description = "Info about purchase")
@Validated
public class Purchase {
    @Null
    @Schema(description = "id", required = false)
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Schema(description = "cakeId", required = true)
    @JsonProperty("cakeId")
    private Long cakeId;

    @NotNull
    @Schema(description = "user's number", required = true)
    @JsonProperty("userNumber")
    private Integer number;
}
