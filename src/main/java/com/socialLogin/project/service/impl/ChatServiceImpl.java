package com.socialLogin.project.service.impl;

import com.socialLogin.project.entity.Chat;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.repository.ChatRepository;
import com.socialLogin.project.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(Users user2,Users reqUser) {

        Chat isExist = chatRepository.findChatByUsers(user2,reqUser);

        if (isExist != null) {
            return isExist;
        }

        Chat chats = new Chat();
        chats.getUsers().add(user2);
        chats.getUsers().add(reqUser);
        chats.setTimestamp(LocalDateTime.now());

        Chat savedChats = chatRepository.save(chats);
        return savedChats;
    }

    @Override
    public Chat chatById(Integer chatId) throws Exception {
        Optional<Chat> optionalChat = chatRepository.findById(chatId);

        if (optionalChat.isEmpty()) {
            throw new Exception("Chat not found with Id" + chatId);
        }
        return optionalChat.get();
    }

    @Override
    public List<Chat> findUserChat(Integer userId) {

        return chatRepository.findChatsByUserId(userId);
    }


}
