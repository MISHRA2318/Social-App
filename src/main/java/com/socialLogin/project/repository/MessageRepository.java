package com.socialLogin.project.repository;

import com.socialLogin.project.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

    List<Message> findChatById(Integer chatId);

}
