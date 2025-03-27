package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;
  
  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  
  public User signup(User user) {
    Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
    if(existingUser.isPresent()){
      throw new RuntimeException("User with this email already exists");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole("ROLE_USER");
    return userRepository.save(user);
  }
  
  public User findByEmail(String email) {
    return userRepository.findByEmail(email).orElse(null);
  }
}
