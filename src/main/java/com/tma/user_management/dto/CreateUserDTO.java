package com.tma.user_management.dto;

import com.tma.user_management.model.UserEmailUnique;
import com.tma.user_management.model.UserUsernameUnique;
import com.tma.user_management.model.enumType.TypeUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDTO {

    @NotNull
    @Size(max = 255)
    @UserUsernameUnique
    private String username;

    @NotNull
    @Size(max = 255)
    private String password;

    @Size(max = 255)
    @UserEmailUnique
    @Email
    private String email;

    @Size(max = 255)
    private String firstname;

    @Size(max = 255)
    private String lastname;

}
