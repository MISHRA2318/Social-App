package com.socialLogin.project.repository;

import com.socialLogin.project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {

     Users findByEmail(String email);
}
