package com.socialLogin.project.controller;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Message;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.service.MessageService;
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
@RequestMapping("api/v1")
public class MessageController {


    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @PostMapping("/create/message/chat/{chatId}")
    public ResponseEntity<BaseResponse<Message>> createMessage(@RequestBody Message message,
                                                              @RequestHeader("Authorization") String token,
                                                              @PathVariable("chatId") Integer chatId) throws Exception {
        Users users = userService.findUserFromToken(token);
        Message messages = messageService.createMessage(users, chatId, message);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), "Message Created ",messages));
    }

    @GetMapping("/messages/chat/{chatId}")
    public ResponseEntity<BaseResponse<List<Message>>> getMessageByChatId(@RequestHeader("Authorization") String token, @PathVariable Integer chatId) throws Exception {
        Users users = userService.findUserFromToken(token);
        List<Message> messages = messageService.findChatMessages(chatId);
        return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(),"Retreived Messages by Id",messages));
    }
}
