package com.socialLogin.project.service;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Users;

import java.util.List;

public interface UserService {

    public Users register(Users users);

    BaseResponse<Users> findById(Integer usersId);

    public BaseResponse<Users> findByemail(String email);

//    BaseResponse<UserResponse> followUser(Integer userId1, Integer userId2) throws Exception;

    public BaseResponse<Users> updateUser(Users user, Integer id);

    public List<Users> searchUser(String query);

    BaseResponse<List<Users>> findAllUsers();

    BaseResponse<Users> findUserFromToken(String token);
}
