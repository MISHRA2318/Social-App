package com.socialLogin.project.service.impl;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Post;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.repository.PostRepository;
import com.socialLogin.project.repository.UserRepository;
import com.socialLogin.project.service.PostService;
import com.socialLogin.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public BaseResponse<Post> createNewPost(Post posts, Integer userId) {

        Users users = userService.findById(userId).data();

        Post newPost = new Post();
        newPost.setCaptions(posts.getCaptions());
        newPost.setImage(posts.getImage());
        newPost.setVideo(posts.getVideo());
        newPost.setUsers(users);
        newPost.setCreatedAt(LocalDateTime.now());
        return new BaseResponse<>(HttpStatus.OK.value()
        ,"Post Created Successfully",
                postRepository.save(newPost));
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
    public BaseResponse<List<Post>> findPostbyUserIdhandling(Integer userId) {
        List<Post> posts = postRepository.findPostByUserId(userId);
        return new BaseResponse<>(HttpStatus.OK.value(),
                "Post Handled by ID"
                ,posts);
    }

    @Override
    public String deletePost(Integer postId, Integer userId) {
        Post post = getPostById(postId);
        Users users = userService.findById(userId).data();

        if (post.getUsers().getUserid() != users.getUserid()) {
            throw new RuntimeException("Wrong User selected");
        }
        postRepository.delete(post);
        return "Post has been deleted Successfully";
    }

//    @Override
//    @Transactional
//    public Post savedPost(Integer postId, Integer userId) {
//
//        Post post = getPostById(postId);
//        UserResponse users = userService.findById(userId).data();
//
//        if (users.getSavedPost().contains(post)) {
//            users.getSavedPost().remove(post);
//        } else {
//            users.getSavedPost().add(post);
//        }
//
//        userRepository.save(users);
//        return post;
//    }

    @Override
    public BaseResponse<Post> likePost(Integer postId, Integer userId) {

        Post post = getPostById(postId);
        Users users = userService.findById(userId).data();

        if (post.getLiked().contains(users)) {
            post.getLiked().remove(users);
        } else {
            post.getLiked().add(users);
        }
        return new BaseResponse<>(HttpStatus.OK.value(),
                "Post is being Liked",
                postRepository.save(post));
    }
}
