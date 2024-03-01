package com.socialLogin.project.service.impl;

import com.socialLogin.project.dto.response.UserResponse;
import com.socialLogin.project.entity.Post;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.repository.PostRepository;
import com.socialLogin.project.repository.UserRepository;
import com.socialLogin.project.service.PostService;
import com.socialLogin.project.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post createNewPost(Post posts, Integer userId) {

        UserResponse users = userService.findById(userId);

        Post newPost = new Post();
        newPost.setCaptions(posts.getCaptions());
        newPost.setImage(posts.getImage());
        newPost.setVideo(posts.getVideo());
        newPost.setUsers(users);
        newPost.setCreatedAt(LocalDateTime.now());
        return postRepository.save(newPost);
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Integer postId) {

        Optional<Post> optionalPost = postRepository.findById(postId);

        System.out.println("Post is "+optionalPost);
        if (optionalPost.isEmpty()) {
            throw new RuntimeException("Post Cannot be null");
        }
        return optionalPost.get();
    }

    @Override
    public List<Post> findPostbyUserIdhandling(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) {
        Post post = getPostById(postId);
        Users users = userService.findById(userId);

        if (post.getUsers().getUserid() != users.getUserid()) {
            throw new RuntimeException("Wrong User selected");
        }
        postRepository.delete(post);
        return "Post has been deleted Successfully";
    }

    @Override
    @Transactional
    public Post savedPost(Integer postId, Integer userId) {

        Post post = getPostById(postId);
        Users users = userService.findById(userId);

        if (users.getSavedPost().contains(post)) {
            users.getSavedPost().remove(post);
        } else {
            users.getSavedPost().add(post);
        }

        userRepository.save(users);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) {

        Post post = getPostById(postId);
        Users users = userService.findById(userId);

        if (post.getLiked().contains(users)) {
            post.getLiked().remove(users);
        } else {
            post.getLiked().add(users);
        }
        userRepository.save(users);
        return post;
    }
}
