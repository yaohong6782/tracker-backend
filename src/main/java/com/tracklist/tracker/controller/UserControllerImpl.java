package com.tracklist.tracker.controller;


import com.tracklist.tracker.api.UsersApi;
import com.tracklist.tracker.dto.UserLoginDTO;
import com.tracklist.tracker.dto.UserLoginResponse;
import com.tracklist.tracker.dto.UserSignUpDTO;
import com.tracklist.tracker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UsersApi {

    private Logger log = LoggerFactory.getLogger(getClass());

    private UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserControllerImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        log.info("User Sign Up initialised");
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserSignUpDTO> usersSignUpPost(@RequestBody UserSignUpDTO userSignUpDTO) {
        log.info("Sign up post called");
        userSignUpDTO.setPassword(passwordEncoder.encode(userSignUpDTO.getPassword()));
        UserSignUpDTO savedUser = userService.createUser(userSignUpDTO);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    public ResponseEntity<UserLoginResponse> usersUserLoginPost(@RequestBody @Validated UserLoginDTO userLoginDTO) {
        log.info("User login post request called : {} " , userLoginDTO);

//        userLoginDTO.setPassword(passwordEncoder.encode(userLoginDTO.getPassword()));
        UserLoginResponse userLoginResponse = userService.userLogin(userLoginDTO);

        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<UserLoginResponse> usersUserLoginPost(UserLoginDTO userLoginDTO) {
//        return UsersApi.super.usersUserLoginPost(userLoginDTO);
//    }
}
