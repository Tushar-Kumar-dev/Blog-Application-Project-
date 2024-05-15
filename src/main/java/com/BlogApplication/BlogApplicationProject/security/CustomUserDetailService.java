package com.BlogApplication.BlogApplicationProject.security;

import com.BlogApplication.BlogApplicationProject.entity.User;
import com.BlogApplication.BlogApplicationProject.exception.ResourceNotFoundException;
import com.BlogApplication.BlogApplicationProject.repositories.UserRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        User user = userRepo.findByEmail(username)
                .orElseThrow(()->new ResourceNotFoundException("user", " email : "+username, 0));
        return user;
    }
}
