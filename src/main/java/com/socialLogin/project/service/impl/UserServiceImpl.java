package com.socialLogin.project.service.impl;

import com.socialLogin.project.config.JwtProvider;
import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.exception.ResourceNotFoundException;
import com.socialLogin.project.repository.UserRepository;
import com.socialLogin.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Users register(Users user) {
        Users users = new Users();
        users.setFirstName(user.getFirstName());
        users.setLastName(user.getLastName());
        users.setEmail(user.getEmail());
        users.setPassword(user.getPassword());
        users.setGender(user.getGender());
        users.setUserid(users.getUserid());

        Users savedUser = userRepository.save(users);
        return savedUser;
    }

    @Override
    public BaseResponse<Users> findById(Integer usersId) {
        Optional<Users> users = userRepository.findById(usersId);

        if (users.isPresent()) {
            return new BaseResponse<>(HttpStatus.OK.value(),
                    "Fetched User By Id",
                    (users.get()));
        } else {
            throw new RuntimeException("User Not exist with this id");
        }
    }

    @Override
    public BaseResponse<Users> findByemail(String email) {
        Users users = userRepository.findByEmail(email);

        return new BaseResponse<>(HttpStatus.OK.value(),
                "User fetched on the basis of Email",
             users);
    }

//    @Override
//    public BaseResponse<UserResponse> followUser(Integer reqUserId, Integer userIdToFollow) throws Exception {
//        Optional<Users> user1 = userRepository.findById(reqUserId);
//        Optional<Users> userToFollow = userRepository.findById(userIdToFollow);
//
//        if(user1.isPresent()){
//            Set<Integer>followersOfUserToFollow=userToFollow.get().getFollowers();
//        }
//
////        Set<Integer> followersOfUserToFollow = userToFollow.getFollowers();
////        if (followersOfUserToFollow == null) {
////            followersOfUserToFollow = new HashSet<>();
////        }else if(followersOfUserToFollow.contains(user1)){
////            followersOfUserToFollow.remove(reqUserId);
////        }else
////            followersOfUserToFollow.add(reqUserId);
////
////            userToFollow.setFollowers(followersOfUserToFollow);
////
////        // Update the following of the current user
////        Set<Integer> followingOfCurrentUser = user1.getFollowing();
////        if (followingOfCurrentUser == null) {
////            followingOfCurrentUser = new HashSet<>();
////        }else if(followingOfCurrentUser.contains(userToFollow)){
////            followingOfCurrentUser.remove(userIdToFollow);
////        }else
////        followingOfCurrentUser.add(userIdToFollow);
////        user1.setFollowing(followingOfCurrentUser);
////
////        userRepository.save(user1);
////        userRepository.save(userToFollow);
////
////        return new BaseResponse<>(HttpStatus.OK.value(),
////                "User have been Followed",
////                user1);
//    }

    public BaseResponse<Users> updateUser(Users user, Integer id) {
        Optional<Users> getUserOptional = userRepository.findById(id);
        if (getUserOptional.isEmpty()) {
            throw new ResourceNotFoundException("User Doesn't Exist with this Id");
        }
        Users existingUser = getUserOptional.get();
        if (user.getFirstName() != null) {
            existingUser.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            existingUser.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }
        if (user.getGender() != null) {
            existingUser.setGender(user.getGender());
        }
        Users updatedUserEntity = Users.builder()
                .firstName(existingUser.getFirstName())
                .lastName(existingUser.getLastName())
                .email(existingUser.getEmail())
                .gender(existingUser.getGender())
                .build();

        Users savedUser = userRepository.save(updatedUserEntity);

        return new BaseResponse<>(HttpStatus.OK.value(), "User Updated", savedUser);
    }

    @Override
    public List<Users> searchUser(String query) {
        return null;
    }

    @Override
    public BaseResponse<List<Users>> findAllUsers() {

        List<Users> users = userRepository.findAll();
        return new BaseResponse<>(HttpStatus.OK.value(),
                "All Users fetched",
                users);
    }

    @Override
    public BaseResponse<Users> findUserFromToken(String token) {
        String email = JwtProvider.getEmailFromToken(token);
        Users user = userRepository.findByEmail(email);

        return BaseResponse.<Users>builder()
                .resultCode(HttpStatus.OK.value())
                .resultMessage("User's data")
                .data(user)
                .build();
    }
}
