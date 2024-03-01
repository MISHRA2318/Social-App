package com.socialLogin.project.controller;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Comment;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.service.CommentService;
import com.socialLogin.project.service.PostService;
import com.socialLogin.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;
    @PostMapping("/create/comments/post/{postId}")
    public ResponseEntity<BaseResponse<Comment>> createComment(@RequestBody Comment comment,
                                                             @RequestHeader("Authorization")String token,
                                                             @PathVariable Integer postId){
     Users users = userService.findUserFromToken(token).data();
     BaseResponse<Comment> createdComments=commentService.createComment(comment,users.getUserid(),postId);
     return ResponseEntity.status(HttpStatus.OK.value()).body(createdComments);
    }

    @PutMapping("/comment/like/{commentId}")
    public ResponseEntity<BaseResponse<Comment>> likeComment(@RequestHeader("Authorization")String token,Integer commentId){
        Users reqUser=userService.findUserFromToken(token).data();
        BaseResponse<Comment> likedComment = commentService.likeComment(commentId,reqUser.getUserid());
        return ResponseEntity.status(HttpStatus.OK.value()).body(likedComment);
    }
}
