package com.socialLogin.project.service;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Reels;

import java.util.List;

public interface ReelsService {

    BaseResponse<Reels> createReel(Reels reels, String token);

    BaseResponse<List<Reels>> findAllReels();
    BaseResponse<List<Reels>> findUserReels(Integer userId) throws Exception;
}
