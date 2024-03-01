package com.socialLogin.project.controller;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.dto.request.ReelRequest;
import com.socialLogin.project.dto.response.ReelResponse;
import com.socialLogin.project.dto.response.UserResponse;
import com.socialLogin.project.entity.Reels;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.service.ReelsService;
import com.socialLogin.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ReelsController {

    @Autowired
    ReelsService reelsService;

    @Autowired
    UserService userService;

    @GetMapping("/reels")
    public ResponseEntity<BaseResponse<List<ReelResponse>>>getAllReels(){
        BaseResponse<List<ReelResponse>> reels =reelsService.findAllReels();
        return ResponseEntity.status(HttpStatus.OK.value()).body(reels);
    }

    public ResponseEntity<BaseResponse<ReelResponse>> createReelHandler(@RequestBody ReelRequest reels,
                                                                        @RequestHeader("Authorization") String token) {
        // Call the service method to handle creating a reel
        BaseResponse<ReelResponse> response = reelsService.createReel(reels, token);

        // Return the response from the service method
        return ResponseEntity.status(response.resultCode()).body(response);
    }

    @GetMapping("/reels/user/{userId}")
    public ResponseEntity<BaseResponse<List<Reels>>> reelsByUserId(@PathVariable("userId") Integer userId) throws Exception {

        List<Reels> reels =reelsService.findUserReels(userId);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(),"Retrived Reels by User ID",reels));
    }

}
