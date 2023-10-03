package com.example.socialmedia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.socialmedia.model.User;
import com.example.socialmedia.repository.UserRepository;

@Service
public class UserServiceDetailsImp implements UserDetailsService{

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userOpt = userRepository.findByUsername(username);
    if(userOpt.isPresent()){
      User user = userOpt.get();
      List<GrantedAuthority> authorities = new ArrayList<>();

      return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), authorities
      );
    }
    
    throw new BadCredentialsException("user not found with username:" + username);
  }
  
}
