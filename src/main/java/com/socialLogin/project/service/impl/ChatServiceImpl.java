package com.socialLogin.project.service.impl;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Chat;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.repository.ChatRepository;
import com.socialLogin.project.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public BaseResponse<Chat> createChat(Users user2, Users reqUser) {

        Chat isExist = chatRepository.findChatByUsers(user2,reqUser);

        if (isExist != null) {
            return new BaseResponse<>(HttpStatus.OK.value(),"Chat Doesn't Exist",null);
        }

        Chat chats = new Chat();
        chats.getUsers().add(user2);
        chats.getUsers().add(reqUser);
        chats.setTimestamp(LocalDateTime.now());

        Chat savedChats = chatRepository.save(chats);
        return new BaseResponse<>(HttpStatus.OK.value(),"Charts Saved",savedChats);
    }

    @Override
    public BaseResponse<Chat> chatById(Integer chatId) throws Exception {
        Optional<Chat> optionalChat = chatRepository.findById(chatId);

        if (optionalChat.isEmpty()) {
            throw new Exception("Chat not found with Id" + chatId);
        }
        return new BaseResponse<>(HttpStatus.OK.value(),
                "Chat found with Id",
                optionalChat.get());
    }

    @Override
    public BaseResponse<List<Chat>> findUserChat(Integer userId) {

        return new BaseResponse<>(HttpStatus.OK.value(),
                "User Chat",
                chatRepository.findChatsByUserId(userId));
    }
}
