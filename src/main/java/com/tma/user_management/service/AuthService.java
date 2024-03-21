package com.tma.user_management.service;

import com.tma.user_management.dto.LoginDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

public interface AuthService {
    public String login(LoginDTO loginDTO);
}
