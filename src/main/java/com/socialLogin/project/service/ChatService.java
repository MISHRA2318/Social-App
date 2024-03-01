package com.socialLogin.project.service;

import com.socialLogin.project.dto.BaseResponse;
import com.socialLogin.project.entity.Chat;
import com.socialLogin.project.entity.Users;

import java.util.List;

public interface ChatService {

    public BaseResponse<Chat> createChat(Users reqUser, Users user2);

    public BaseResponse<Chat> chatById(Integer chatId) throws Exception;

    public BaseResponse<List<Chat>> findUserChat(Integer userId);
}
