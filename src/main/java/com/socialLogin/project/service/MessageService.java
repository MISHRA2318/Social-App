package com.socialLogin.project.service;

import com.socialLogin.project.entity.Message;
import com.socialLogin.project.entity.Users;

import java.util.List;

public interface MessageService {
    public Message createMessage(Users users, Integer chatId, Message  chat) throws Exception;
    List<Message> findChatMessages(Integer chatId) throws Exception;
}
