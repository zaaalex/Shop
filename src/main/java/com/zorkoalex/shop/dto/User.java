package com.zorkoalex.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Schema(description = "Info about purchase")
@Validated
public class User {
    @Null
    @Schema(description = "id", required = false)
    @JsonProperty("id")
    private Long id;

    @NotNull
    @Schema(description = "name", required = true)
    @JsonProperty("name")
    private String name;

    @NotNull
    @Schema(description = "number", required = true)
    @JsonProperty("number")
    private Integer number;
}
