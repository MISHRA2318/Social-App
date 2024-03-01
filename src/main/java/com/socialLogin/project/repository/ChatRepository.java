package com.socialLogin.project.repository;

import com.socialLogin.project.entity.Chat;
import com.socialLogin.project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat,Integer> {

    @Query(value = "SELECT c FROM Chat c JOIN c.users u WHERE u.userid = :userId")
    List<Chat> findChatsByUserId(@Param("userId") Integer userId);

    @Query(value = "SELECT c FROM Chat c JOIN c.users u WHERE :user MEMBER OF c.users AND :reqUser MEMBER OF c.users")
    Chat findChatByUsers(@Param("user") Users user, @Param("reqUser") Users reqUser);
}
