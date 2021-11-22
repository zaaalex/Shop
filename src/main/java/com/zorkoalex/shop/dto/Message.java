package com.zorkoalex.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Schema
@Validated
public class Message {
    @NotNull
    @Schema(description = "message", required = true)
    @JsonProperty("message")
    String message;

    public Message (String message){
        this.message=message;
    }
}
