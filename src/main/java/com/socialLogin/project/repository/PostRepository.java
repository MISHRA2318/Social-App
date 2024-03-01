package com.socialLogin.project.repository;

import com.socialLogin.project.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    @Query(value = "select p from post p where p.users_userId=:userId",nativeQuery = true)
    List<Post> findPostByUserId(@Param("userId") Integer userId);
}
