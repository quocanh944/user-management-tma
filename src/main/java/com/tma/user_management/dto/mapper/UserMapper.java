package com.tma.user_management.dto.mapper;

import com.tma.user_management.dto.CreateUserDTO;
import com.tma.user_management.dto.UserDTO;
import com.tma.user_management.model.User;
import com.tma.user_management.model.enumType.TypeUser;

public class UserMapper {
    public static UserDTO mapCreateUserDTO2UserDTO(CreateUserDTO createUserDTO) {
      return UserDTO.builder()
              .username(createUserDTO.getUsername())
              .password(createUserDTO.getPassword())
              .email(createUserDTO.getEmail())
              .firstname(createUserDTO.getFirstname())
              .lastname(createUserDTO.getLastname())
              .type(TypeUser.USER)
              .build();
    };
    public static User mapToEntity(final UserDTO userDTO, final User user) {
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setType(userDTO.getType());
        user.setEmail(userDTO.getEmail());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        return user;
    }

    public static UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setType(user.getType());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        return userDTO;
    }
}
