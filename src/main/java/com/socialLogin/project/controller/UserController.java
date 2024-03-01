package com.socialLogin.project.controller;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.repository.UserRepository;
import com.socialLogin.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<BaseResponse<List<Users>>> getAllUsers()
    {
        BaseResponse<List<Users>> users = userService.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK.value()).body(users);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<BaseResponse<Users>> getUserbyIdHandler(@PathVariable("userId") Integer id) {

        BaseResponse<Users> savedUser = userService.findById(id);

        return ResponseEntity.status(HttpStatus.OK.value()).body(savedUser);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<BaseResponse<Users>> getUserbyEmailHandler(@PathVariable("email") String email){
        BaseResponse<Users> savedUser=userService.findByemail(email);
        return ResponseEntity.status(HttpStatus.OK.value()).body(savedUser);
    }

    @PutMapping("/users")
    public ResponseEntity<BaseResponse<Users>> updateUserHandler(@RequestBody Users user, @RequestHeader("Authorization")String token) {
        Users reqUser = userService.findUserFromToken(token).data();
        BaseResponse<Users> updatedUser = userService.updateUser(user,reqUser.getUserid());
        return ResponseEntity.status(HttpStatus.OK.value()).body(updatedUser);
    }

//    @PutMapping("/users/follow/{userId2}")
//    public ResponseEntity<BaseResponse<UserResponse>> followUserhandler(@RequestHeader("Authorization")String token,@PathVariable Integer userId2) throws Exception {
//
//        UserResponse reqUserId=userService.findUserFromToken(token).data();
//        BaseResponse<UserResponse> user=userService.followUser(reqUserId.getUserid(),userId2);
//        return ResponseEntity.status(HttpStatus.OK.value()).body(user);
//    }

    @PostMapping("/user/search")
    public Users getUserbyName(){
        return null;
    }

    @GetMapping("/user/profile")
    public BaseResponse<Users> getUserWithProfile(@RequestHeader("Authorization")String token){
        Users user = userService.findUserFromToken(token).data();
      return new BaseResponse<>(
              HttpStatus.OK.value(),
              "User with Profile",
              user);
    }
}