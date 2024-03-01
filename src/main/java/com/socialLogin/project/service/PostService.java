package com.socialLogin.project.service;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Post;

import java.util.List;

public interface PostService {
    BaseResponse<Post> createNewPost(Post posts,Integer userId);
    List<Post> findAllPosts();
    Post getPostById(Integer postId);
    BaseResponse<List<Post>>findPostbyUserIdhandling(Integer userId);
    String deletePost(Integer postId,Integer userId);
//    Post savedPost(Integer postId,Integer userId);
    BaseResponse<Post> likePost(Integer postId,Integer userId);
}
