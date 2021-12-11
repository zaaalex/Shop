package com.zorkoalex.shop.dto.cake;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Schema(description = "Info about cakes")
@Validated
public class Cakes {
    @NotNull
    @Schema(description = "cake_list", required = true)
    @JsonProperty("cake_list")
    private List<Cake> cakeList;
}
