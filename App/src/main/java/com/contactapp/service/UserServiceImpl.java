package com.contactapp.service;

import com.contactapp.data.model.User;
import com.contactapp.data.repository.UserRepository;
import com.contactapp.dto.requests.LogOutRequest;
import com.contactapp.dto.requests.LoginRequest;
import com.contactapp.dto.requests.RegisterRequest;
import com.contactapp.dto.response.LogOutResponse;
import com.contactapp.dto.response.LoginResponse;
import com.contactapp.dto.response.RegisterResponses;
import com.contactapp.exception.EmptySpaceException;
import com.contactapp.exception.UserDetailsEmpty;
import com.contactapp.exception.UserIsPresentException;
import com.contactapp.exception.UserLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public RegisterResponses registerUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPhoneNo(registerRequest.getPhoneNo());
        user.setGender(registerRequest.getGender());
        userRegisterValidation(user);
        userRepository.save(user);
        RegisterResponses registerResponses = new RegisterResponses();
        registerResponses.setMessage("User successfully registered");
        return registerResponses;
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        userLoginValidation(loginRequest);
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if(user.isLoggedIn() == false) {
                user.setLoggedIn(true);
                userRepository.save(user);
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setMessage("you are logged in");
                return loginResponse;
            }else{
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setMessage("you are already logged in");
            }
        }
        return null;
    }

    @Override
    public LogOutResponse logOutUser(LogOutRequest logOutRequest) {
        userLogOutValidation(logOutRequest);
        Optional<User> userOptional = userRepository.findByEmail(logOutRequest.getEmail());
        if (userOptional.isPresent()){
            User user = userOptional.get();
            if(user.isLoggedIn()==true) {
                user.setLoggedIn(false);
                userRepository.save(user);
                LogOutResponse logOutResponse = new LogOutResponse();
                logOutResponse.setMessage("you are logged out");
                return logOutResponse;
            }
        }
        return null;
    }



    private void userRegisterValidation(User user){
        if(user.getName().isEmpty() || user.getPhoneNo().isEmpty() || user.getEmail().isEmpty() || user.getGender().isEmpty()){
            throw new UserDetailsEmpty("one field is empty");
        }

        if(user.getName().equals(" ") || user.getPhoneNo().equals(" ") || user.getEmail().equals(" ") || user.getGender().equals(" ")) {
            throw new EmptySpaceException("Can not take white spaces");
        }

        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        if (userOptional.isPresent()){
            throw new UserIsPresentException("User already exist");
        }
    }

    private void userLoginValidation(LoginRequest loginRequest) {
        if(loginRequest.getEmail().isEmpty()){
            throw new UserLoginException("one field is empty");
        }

        if(loginRequest.getEmail().equals(" ")){
            throw new EmptySpaceException("Can not take white spaces");
        }
    }

    private void userLogOutValidation(LogOutRequest logOutRequest) {
        if(logOutRequest.getEmail().isEmpty()){
            throw new UserLoginException("one field is empty");
        }

        if(logOutRequest.getEmail().equals(" ")){
            throw new EmptySpaceException("Can not take white spaces");
        }
    }
}
