package com.socialLogin.project.repository;

import com.socialLogin.project.entity.Reels;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReelsRepository extends JpaRepository<Reels,Integer>{

    @Query(value = "select r from Reels r where r.users.userid = :userId")
    List<Reels> findByUserId(Integer userId);
}