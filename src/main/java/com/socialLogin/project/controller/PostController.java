package com.socialLogin.project.controller;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Post;
import com.socialLogin.project.dto.response.ApiResponse;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.service.PostService;
import com.socialLogin.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    public PostService postService;

    @Autowired
    public UserService userService;

    @GetMapping("/post")
    public ResponseEntity<BaseResponse<List<Post>>> getAllPost() {
        List<Post> posts = postService.findAllPosts();
        return ResponseEntity.ok(new BaseResponse<>
                (HttpStatus.OK.value(),
                        "Fetched Successfully",
                        posts));
    }

    @PostMapping("/post/user")
    public ResponseEntity<BaseResponse<Post>> createNewPostHandler(@RequestBody Post post,@RequestHeader("Authorization")String token) {
        Users reqUser = userService.findUserFromToken(token).data();
        BaseResponse<Post> createdPost = postService.createNewPost(post,reqUser.getUserid());
        return ResponseEntity.status(HttpStatus.OK.value()).body(createdPost);
    }

    @GetMapping("/post/user")
    public ResponseEntity<BaseResponse<List<Post>>> findPostByUserIdHandler(@RequestHeader("Authorization")String token) {
        Users user = userService.findUserFromToken(token).data();
        BaseResponse<List<Post>> post = postService.findPostbyUserIdhandling(user.getUserid());
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<BaseResponse<Post>> findPostbyIdHandler(@PathVariable Integer postId) {
        Post post = postService.getPostById(postId);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "Retrived Post By Id",post));
    }

//    @PutMapping("/post/save/{postId}")
//    public ResponseEntity<BaseResponse<Post>> savedPostHandler(@PathVariable Integer postId,@RequestHeader("Authorization")String token) {
//       UserResponse reqUser = userService.findUserFromToken(token).data();
//        Post post = postService.savedPost(postId, reqUser.getUserid());
//        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "User Post Saved ",post));
//    }

    @PutMapping("/post/like/{postId}")
    public ResponseEntity<BaseResponse<Post>> likedPostHandler(@PathVariable Integer postId,@RequestHeader("Authorization")String token) {
        Users reqUser = userService.findUserFromToken(token).data();
        BaseResponse<Post> post = postService.likePost(postId, reqUser.getUserid());
        return ResponseEntity.status(HttpStatus.OK.value()).body(post);
    }

    @DeleteMapping("/post/{postId}")
    public ResponseEntity<BaseResponse<ApiResponse>> deletePostHandler(@RequestHeader("Authorization")String token, @PathVariable Integer postId) {
        Users reqUser = userService.findUserFromToken(token).data();
        String message = postService.deletePost(reqUser.getUserid(), postId);
        ApiResponse response=new ApiResponse(message,true);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "Post Deleted",response));
    }
}
