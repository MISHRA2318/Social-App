package com.socialLogin.project.controller;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.dto.request.ChatRequest;
import com.socialLogin.project.dto.response.UserResponse;
import com.socialLogin.project.entity.Chat;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.service.ChatService;
import com.socialLogin.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ChatController {

    @Autowired
    ChatService chatService;

    @Autowired
    UserService userService;

    @PostMapping("/create/chats")
    public ResponseEntity<BaseResponse<Chat>>createChat(@RequestHeader("Authorization")String token
                                          , @RequestBody ChatRequest chatRequest){

        UserResponse reqUsers = userService.findUserFromToken(token);
        Users users=userService.findById(chatRequest.getUserId());

        Chat chat = chatService.createChat(reqUsers,users);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(),"Chat Room Created",chat));
    }

    @GetMapping("/chat/user")
    public ResponseEntity<BaseResponse<List<Chat>>> getChatById(@RequestHeader("Authorization")String token){

        Users users = userService.findUserFromToken(token);

        List<Chat> chats = chatService.findUserChat(users.getUserid());
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(),"Chats of User  by Id",chats));
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<BaseResponse<Chat>> chatById(@PathVariable("chatId") Integer chatId) throws Exception {
        Chat chats = chatService.chatById(chatId);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(),"Chats by Chat Id",chats));
    }

}
