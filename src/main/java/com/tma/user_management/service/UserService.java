package com.tma.user_management.service;

import com.tma.user_management.dto.ChangePasswordDTO;
import com.tma.user_management.dto.UpdateProfileDTO;
import com.tma.user_management.model.User;
import com.tma.user_management.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.SimpleTimeZone;

public interface UserService {

    public Page<UserDTO> getUsers(Integer page, Integer size, String search);
    public UserDTO get(final Long id);
    public UserDTO get(final String username);
    public Long create(final UserDTO userDTO);
    public void changePassword(ChangePasswordDTO changePasswordDTO, String username);
    public void delete(final Long id);
    public boolean usernameExists(final String username);
    public boolean emailExists(final String email);
    UserDTO updateProfile(UpdateProfileDTO updateProfileDTO, String username);
}
