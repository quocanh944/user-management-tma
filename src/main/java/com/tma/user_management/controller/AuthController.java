package com.tma.user_management.controller;

import com.tma.user_management.dto.*;
import com.tma.user_management.security.JWTTokenProvider;
import com.tma.user_management.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import com.tma.user_management.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private AuthService authService;
    private JWTTokenProvider jwtTokenProvider;
    private UserService userService;

    @PostMapping("/login")
    @SecurityRequirements()
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {

        String token = authService.login(loginDTO);

        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @GetMapping("profile")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<UserDTO> readProfile(Authentication authentication) {
        return new ResponseEntity<>(userService.get(authentication.getName()), HttpStatus.OK);
    }

    @PostMapping("changePassword")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<String> changePassword(
            @RequestBody @Valid final ChangePasswordDTO changePasswordDTO,
            Authentication authentication
    ) {
        userService.changePassword(changePasswordDTO, authentication.getName());

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateProfile(
            @RequestBody @Valid final UpdateProfileDTO updateProfileDTO,
            Authentication authentication
    ) {
        UserDTO updatedUserDTO = userService.updateProfile(updateProfileDTO, authentication.getName());
        return new ResponseEntity<>(updatedUserDTO, HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthResponseDTO> refreshToken(
            Authentication authentication
    ) {
        return new ResponseEntity<>(
                new AuthResponseDTO(
                        jwtTokenProvider.createToken(authentication)
                ),
                HttpStatus.OK
        );
    }
}
