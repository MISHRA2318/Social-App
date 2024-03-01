package com.socialLogin.project.service.impl;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Reels;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.repository.ReelsRepository;
import com.socialLogin.project.service.ReelsService;
import com.socialLogin.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReelsServiceImpl implements ReelsService {

    @Autowired
    private ReelsRepository reelsRepository;

    @Autowired
    private UserService userService;

    @Override
    public BaseResponse<Reels> createReel(Reels reels, String token) {
        // Retrieve user information from token
        Users userResponse = userService.findUserFromToken(token).data();

        // Construct Reels object from ReelRequest
        Reels reelsEntity = Reels.builder()
                .title(reels.getTitle())
                .video(reels.getVideo())
                .users(Users.builder().userid(userResponse.getUserid()).build()) // Assuming you need to associate the reel with the user
                .build();

        // Save the reel to the database
        Reels createdReel = reelsRepository.save(reelsEntity);

        // Construct ReelResponse from created Reels object
        Reels reelResponse = Reels.builder()
                .title(createdReel.getTitle())
                .video(createdReel.getVideo())
                .users(userResponse)
                .build();

        // Create and return the BaseResponse
        return new BaseResponse<>(HttpStatus.OK.value(), "Reel Created", reelResponse);
    }

    @Override
    public BaseResponse<List<Reels>> findAllReels() {
        List<Reels>reels=reelsRepository.findAll();

        return  new BaseResponse<>(HttpStatus.OK.value(),
                "Fetched All Reels",
                reels);
    }

    @Override
    public BaseResponse<List<Reels>> findUserReels(Integer userId) throws Exception {

        List<Reels> reels = reelsRepository.findByUserId(userId);

        if (reels.isEmpty()){
            throw new Exception("No Reel Found of this User");
        }
        return new BaseResponse<>(HttpStatus.OK.value(),
                "User Id via Id",reels);
    }
}
