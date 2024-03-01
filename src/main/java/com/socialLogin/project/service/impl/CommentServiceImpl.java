package com.socialLogin.project.service.impl;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.dto.response.UserResponse;
import com.socialLogin.project.entity.Comment;
import com.socialLogin.project.entity.Post;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.repository.CommentRepository;
import com.socialLogin.project.repository.PostRepository;
import com.socialLogin.project.service.CommentService;
import com.socialLogin.project.service.PostService;
import com.socialLogin.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public Comment createComment(Comment comment, Integer userId, Integer postId) {

        Users users = userService.findById(userId);
        Post post = postService.getPostById(postId);

        comment.setUsers(users);
        comment.setComments(comment.getComments());
        comment.setLikedComments(comment.getLikedComments());
        comment.setLocalDateTime(LocalDateTime.now());
        List<Comment>comments =post.getComments();
        comments.add(comment);
        Comment savedComment = commentRepository.save(comment);
        post.setComments(comments);
        postRepository.save(post);

        return savedComment ;
    }

    @Override
    public Comment findCommentById(Integer commentId) {
        Optional<Comment> comment=commentRepository.findById(commentId);

        if(comment.isEmpty()){
            return null;
        }
        return comment.get();
    }

    @Override
    public Comment likeComment(Integer commentId, Integer userId) {
        BaseResponse<UserResponse> users = userService.findById(userId);
        Comment comment = findCommentById(commentId);

        if(!comment.getLikedComments().contains(users)){
            comment.getLikedComments().add(users);
        }else {
            comment.getLikedComments().remove(users);
        }

        return commentRepository.save(comment);
    }
}
