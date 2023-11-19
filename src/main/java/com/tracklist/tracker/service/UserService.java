package com.tracklist.tracker.service;

import com.tracklist.tracker.config.JwtService;
import com.tracklist.tracker.dto.UserLoginDTO;
import com.tracklist.tracker.dto.UserLoginResponse;
import com.tracklist.tracker.dto.UserSignUpDTO;
import com.tracklist.tracker.dto.UsersDTO;
import com.tracklist.tracker.entity.Users;
import com.tracklist.tracker.mapper.UserMapper;
import com.tracklist.tracker.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private final UsersRepository usersRepository;
    private UserMapper userMapper;

    private final JwtService jwtService;
    private AuthenticationManager authenticationManager;


    public UserService(UsersRepository usersRepository, UserMapper userMapper,JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        log.info("User sign up service initialised");
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    public boolean usernameExist(String username) {
        return usersRepository.existsByUsernameIgnoreCase(username);
    }
    public UserSignUpDTO createUser(UserSignUpDTO userSignUpDTO) {
        log.info("create user function called");
        log.info("user sign up dto : {} ", userSignUpDTO.getUsername());
        if (usernameExist(userSignUpDTO.getUsername())){
            throw new RuntimeException("Username already exists");
        }

        Users user = userMapper.mapUserSignUpDTOTOUsersEntity(userSignUpDTO);
        Users savedUser = usersRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        log.info("saved user : {} " , savedUser);

        log.info("jwt token : {} ", jwtToken);

        UserSignUpDTO userDTO = userMapper.mapUsersEntityToUserSignUpDTO(user);
        return userDTO;
    }

    public UsersDTO findById(Long userId){
        Users user = usersRepository.findById(userId).orElseThrow(()
            -> new RuntimeException("No user found"));

        UsersDTO userDTO = new UsersDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    public Optional<UsersDTO> findByUsername(String username){
        log.info("Finding user by username function called : {} ", username);

        Users userFound = usersRepository.findByUsername(username).orElseThrow(()
            -> new RuntimeException("Username is invalid or incorrect"));

        UsersDTO usersDTO = userMapper.usersToUsersDTO(userFound);
        log.info("user dto after finding by username : {} ", usersDTO);


        return Optional.of(usersDTO);
    }

//    public UserLoginResponse authenticate(UserLoginDTO userLoginDTO) {
//
//    }

    public UserLoginResponse userLogin(UserLoginDTO userLoginDTO) {
        log.info("user logging in service values :");
        log.info("username : {} , password : {} ", userLoginDTO.getUsername(), userLoginDTO.getPassword());


        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userLoginDTO.getUsername(),
                               userLoginDTO.getPassword()
                        )
                );

        var user = usersRepository.findByUsername(userLoginDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Unknown User"));

        log.info("user found : {} " , user.toString());
        var jwtToken = jwtService.generateToken(user);
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setAccessToken(jwtToken);
        return userLoginResponse;
    }
}
