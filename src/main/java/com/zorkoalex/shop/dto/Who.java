package com.zorkoalex.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Schema(description = "Info keycloak")
@Validated
public class Who{
    @NotNull
    @Schema(description = "name", required = true)
    @JsonProperty("name")
    private String name;

    public Who (String name){
        this.name=name;
    }
}
