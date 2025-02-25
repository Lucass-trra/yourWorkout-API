package com.app.yourWorkout.DTO.request.user;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserCreateRequest(
        String name,
        String email,
        String password
) {}
