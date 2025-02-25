package com.fatihmayuk.issuemanagement.service.impl;

import com.fatihmayuk.issuemanagement.entity.User;
import com.fatihmayuk.issuemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password. ");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),Arrays.asList(new SimpleGrantedAuthority("USER")));

    }
}
