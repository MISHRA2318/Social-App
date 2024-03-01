package com.socialLogin.project.service;

import com.socialLogin.project.entity.Post;

import java.util.List;

public interface PostService {
    Post createNewPost(Post posts,Integer userId);
    List<Post> findAllPosts();
    Post getPostById(Integer postId);
    List<Post>findPostbyUserIdhandling(Integer userId);
    String deletePost(Integer postId,Integer userId);
    Post savedPost(Integer postId,Integer userId);
    Post likePost(Integer postId,Integer userId);
}
