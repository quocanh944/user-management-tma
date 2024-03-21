package com.tma.user_management.dto;

import com.tma.user_management.model.UserEmailUnique;
import com.tma.user_management.model.UserUsernameUnique;
import com.tma.user_management.model.enumType.TypeUser;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class UserDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    @UserUsernameUnique
    private String username;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    private TypeUser type;

    @Size(max = 255)
    @UserEmailUnique
    private String email;

    @Size(max = 255)
    private String firstname;

    @Size(max = 255)
    private String lastname;

}
