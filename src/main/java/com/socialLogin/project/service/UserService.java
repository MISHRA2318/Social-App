package com.socialLogin.project.service;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.dto.request.UserRequest;
import com.socialLogin.project.dto.response.UpdatedUsers;
import com.socialLogin.project.dto.response.UserResponse;
import com.socialLogin.project.entity.Users;

import java.util.List;

public interface UserService {

    public Users register(Users users);

    BaseResponse<UserResponse> findById(Integer usersId);

    public BaseResponse<UserResponse> findByemail(String email);

//    BaseResponse<UserResponse> followUser(Integer userId1, Integer userId2) throws Exception;

    public BaseResponse<UpdatedUsers> updateUser(UserRequest user, Integer id);

    public List<Users> searchUser(String query);

    BaseResponse<List<UserResponse>> findAllUsers();

    BaseResponse<UserResponse> findUserFromToken(String token);
}
