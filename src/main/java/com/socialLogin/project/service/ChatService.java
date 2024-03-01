package com.socialLogin.project.service;

import com.socialLogin.project.entity.Chat;
import com.socialLogin.project.entity.Users;

import java.util.List;

public interface ChatService {

    public Chat createChat(Users reqUser, Users user2);

    public Chat chatById(Integer chatId) throws Exception;

    public List<Chat> findUserChat(Integer userId);
}
