package com.socialLogin.project.service;

import com.socialLogin.project.entity.Comment;

public interface CommentService {

    Comment createComment(Comment comment,Integer userId,Integer postId);
    Comment findCommentById(Integer commentId);
    Comment likeComment(Integer commentId,Integer userId);
}
