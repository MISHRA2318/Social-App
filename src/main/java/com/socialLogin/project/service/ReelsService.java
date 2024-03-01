package com.socialLogin.project.service;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.dto.request.ReelRequest;
import com.socialLogin.project.dto.response.ReelResponse;
import com.socialLogin.project.entity.Reels;
import com.socialLogin.project.entity.Users;

import java.util.List;

public interface ReelsService {

    BaseResponse<ReelResponse> createReel(ReelRequest reels, String token);

    BaseResponse<List<ReelResponse>> findAllReels();
    List<Reels> findUserReels(Integer userId) throws Exception;
}
