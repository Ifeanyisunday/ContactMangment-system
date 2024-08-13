package com.contactapp.service;

import com.contactapp.dto.requests.RegisterRequest;
import com.contactapp.dto.response.RegisterResponses;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    RegisterResponses registerUser(RegisterRequest registerRequest);
    LoginResponse loginUser(LoginRequest loginRequest);
    LogOutResponse logOutUser(LogOutRequest logOutRequest);
}
