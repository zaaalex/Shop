package com.zorkoalex.shop.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Schema(description = "Info about users")
@Validated
public class Users {
    @NotNull
    @Schema(description = "user_list", required = true)
    @JsonProperty("user_list")
    private List<User> userList;
}
