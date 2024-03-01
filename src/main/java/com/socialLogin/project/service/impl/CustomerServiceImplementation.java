package com.socialLogin.project.service.impl;

import com.socialLogin.project.entity.Users;
import com.socialLogin.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImplementation implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Users user=userRepository.findByEmail(username);

       if(user==null){
           throw new UsernameNotFoundException("User not found with the email");
       }

        List<GrantedAuthority>authorities=new ArrayList<>();
       return new User(user.getEmail(),user.getPassword(),authorities);
    }
}
