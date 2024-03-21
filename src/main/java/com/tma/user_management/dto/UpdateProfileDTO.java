package com.tma.user_management.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateProfileDTO {
    @Email
    private String email;
    private String firstname;
    private String lastname;
}
