package com.socialLogin.project.controller;

import com.socialLogin.project.config.JwtProvider;
import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.dto.request.LoginRequest;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.repository.UserRepository;
import com.socialLogin.project.dto.response.AuthResponse;
import com.socialLogin.project.service.UserService;
import com.socialLogin.project.service.impl.CustomerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    public UserService userService;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerServiceImplementation customerServiceImplementation;

    @PostMapping("/signup")
    public BaseResponse<AuthResponse> createUsersHandler(@RequestBody Users user) throws Exception {

        Users isExist = userRepository.findByEmail(user.getEmail());

        if (isExist != null) {
            throw new Exception("This Email is already existsq");
        }

        Users users = new Users();

        users.setFirstName(user.getFirstName());
        users.setLastName(user.getLastName());
        users.setEmail(user.getEmail());
        users.setPassword(passwordEncoder.encode(user.getPassword()));
        users.setGender(user.getGender());

        Users savedUser = userRepository.save(users);

        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getEmail());

        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token, "Registerd Success");
        return new BaseResponse<>(
                HttpStatus.OK.value(),
                "Registerd Success",
                res);
    }

    @PostMapping("/signin")
    public BaseResponse<AuthResponse> signinResponse(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token, "Login Success");
        return new BaseResponse<>(HttpStatus.OK.value(), "Login Success",res);
    }

    private Authentication authenticate(String email, String password) throws Exception {
        UserDetails userDetails = customerServiceImplementation.loadUserByUsername(email);

        if (userDetails == null) {
            throw new Exception("Invalid Username");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Wrong Credentials");
        }
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities()) ;
    }
}
