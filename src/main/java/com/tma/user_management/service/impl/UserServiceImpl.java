package com.tma.user_management.service.impl;

import com.tma.user_management.dto.ChangePasswordDTO;
import com.tma.user_management.dto.UpdateProfileDTO;
import com.tma.user_management.dto.mapper.UserMapper;
import com.tma.user_management.model.User;
import com.tma.user_management.dto.UserDTO;
import com.tma.user_management.model.enumType.TypeUser;
import com.tma.user_management.repository.UserRepository;
import com.tma.user_management.service.UserService;
import com.tma.user_management.util.BadInputException;
import com.tma.user_management.util.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.BrokenBarrierException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO, String username) throws UsernameNotFoundException, BadInputException {
        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new BadInputException("New Password and Confirm Password not match");
        }

        User u = userRepository
                .findUserByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Not found user")
                );

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!(encoder.matches(changePasswordDTO.getCurrentPassword(), u.getPassword()))
        ) {
            throw new BadInputException("Wrong password");
        }

        u.setPassword(encoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(u);
    }

    @Override
    public Page<UserDTO> getUsers(Integer page, Integer size, String search) {
        return userRepository
                .findUserByType(search, TypeUser.USER, PageRequest.of(page, size))
                .map(user -> UserMapper.mapToDTO(user, UserDTO.builder().build()));
    }

    public UserDTO get(final Long id) {
        return userRepository.findById(id)
                .map(user -> UserMapper.mapToDTO(user, UserDTO.builder().build()))
                .orElseThrow(NotFoundException::new);
    }

    public UserDTO get(final String username) {
        return userRepository.findUserByUsername(username)
                .map(user -> UserMapper.mapToDTO(user, UserDTO.builder().build()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        UserMapper.mapToEntity(userDTO, user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user).getId();
    }

    public void delete(final Long id) {
        Optional<User> u = userRepository.findById(id);
        if (u.isPresent()) {
            if (u.get().getType() == TypeUser.ADMIN) {
                throw new BadInputException("Cant not delete Admin");
            }
            userRepository.deleteById(id);
        }
    }

    public boolean usernameExists(final String username) {
        return userRepository.existsByUsernameIgnoreCase(username);
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    @Override
    public UserDTO updateProfile(UpdateProfileDTO updateProfileDTO, String username) {
        User user = userRepository
                .findUserByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Not found user")
                );

        user.setEmail(
                updateProfileDTO.getEmail() != null &&
                        !updateProfileDTO.getEmail().isEmpty()
                ? updateProfileDTO.getEmail() : user.getEmail()
        );
        user.setFirstname(
                updateProfileDTO.getFirstname() != null &&
                        !updateProfileDTO.getFirstname().isEmpty()
                ? updateProfileDTO.getFirstname() : user.getFirstname()
        );
        user.setLastname(
                updateProfileDTO.getLastname() != null &&
                        !updateProfileDTO.getLastname().isEmpty()
                ? updateProfileDTO.getLastname() : user.getLastname());

        userRepository.save(user);
        return UserMapper.mapToDTO(user, UserDTO.builder().build());
    }

}

