package com.app.yourWorkout.DTO.request.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserCreateRequest(
        @NotBlank(message = "'name' is required and can not be empty or blank")
        String name,

        @NotBlank(message = "'email' is required and can not be empty or blank")
        String email,

        @NotBlank(message = "'password' is required and can not be empty or blank")
        String password
) {}
