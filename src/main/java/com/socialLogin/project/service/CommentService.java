package com.socialLogin.project.service;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Comment;

public interface CommentService {

    BaseResponse<Comment> createComment(Comment comment,Integer userId,Integer postId);
    Comment findCommentById(Integer commentId);
    BaseResponse<Comment> likeComment(Integer commentId, Integer userId);
}
