package com.contactapp.controller;

import com.contactapp.dto.requests.CreateContactRequest;
import com.contactapp.dto.requests.LogOutRequest;
import com.contactapp.dto.requests.LoginRequest;
import com.contactapp.dto.requests.RegisterRequest;
import com.contactapp.dto.response.ApiResponse;
import com.contactapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v2/contactUser")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<?> createContact(@RequestBody RegisterRequest registerRequest) {
        try {
            var result = userService.registerUser(registerRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),
                    BAD_REQUEST);
        }
    }

    @PatchMapping("/loginUser")
    public ResponseEntity<?> createContact(@RequestBody LoginRequest loginRequest) {
        try {
            var result = userService.loginUser(loginRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),
                    BAD_REQUEST);
        }
    }

    @PatchMapping("/logoutUser")
    public ResponseEntity<?> createContact(@RequestBody LogOutRequest logOutRequest) {
        try {
            var result = userService.logOutUser(logOutRequest);
            return new ResponseEntity<>(new ApiResponse(true, result), CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),
                    BAD_REQUEST);
        }
    }
}
