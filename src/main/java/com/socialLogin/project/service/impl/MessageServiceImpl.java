package com.socialLogin.project.service.impl;

import com.socialLogin.project.entity.Chat;
import com.socialLogin.project.entity.Message;
import com.socialLogin.project.entity.Users;
import com.socialLogin.project.repository.ChatRepository;
import com.socialLogin.project.repository.MessageRepository;
import com.socialLogin.project.service.ChatService;
import com.socialLogin.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChatService chatService;

    @Autowired
    ChatRepository chatRepository;
    @Override
    public Message createMessage(Users users, Integer chatId, Message chat) throws Exception {

        Message message= new Message();

        System.out.println(users);
        Chat chats = chatService.chatById(chatId);
        message.setChat(chats);
        message.setContent(chat.getContent());
        message.setImage(chat.getImage());
        message.setUsers(users);
        message.setLocalDateTime(LocalDateTime.now());
        Message savedMessage =  messageRepository.save(message);
        chats.getMessages().add(savedMessage);
        chatRepository.save(chats);
        return savedMessage;
    }

    @Override
    public List<Message> findChatMessages(Integer chatId) throws Exception {

        Chat chat = chatService.chatById(chatId);

        if(chat.getId()==null){
            throw new Exception("Chat not found");
        }
        return messageRepository.findChatById(chatId);
    }
}
