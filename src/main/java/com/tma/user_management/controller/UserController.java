package com.tma.user_management.controller;

import com.tma.user_management.dto.CreateUserDTO;
import com.tma.user_management.dto.UserDTO;
import com.tma.user_management.dto.mapper.UserMapper;
import com.tma.user_management.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    @PostMapping("createUser")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createUser(@RequestBody @Valid final CreateUserDTO createUserDTO) {
        UserDTO userDTO = UserMapper.mapCreateUserDTO2UserDTO(createUserDTO);
        final Long createdId = userService.create(userDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") final Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("readUser")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Page<UserDTO>> readUser(
            @RequestParam(
                    value = "page",
                    defaultValue = "0",
                    required = false
            ) Integer page,
            @RequestParam(
                    value = "size",
                    defaultValue = "5",
                    required = false
            ) Integer size,
            @RequestParam(
                    value = "search",
                    defaultValue = "",
                    required = false
            ) String search
    ) {
        Page<UserDTO> res = userService.getUsers(page, size, search);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
