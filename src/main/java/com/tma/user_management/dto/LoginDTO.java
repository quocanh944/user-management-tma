package com.tma.user_management.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {
    @NotNull
    private String username;

    @NotNull
    private String password;
}
