package com.example.demo.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ForgotPasswordRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignupRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

  @Autowired
  private UserService userService;
  
  @Autowired
  private AuthenticationManager authenticationManager;
  
  @Autowired
  private CustomUserDetailsService customUserDetailsService;
  
  @Autowired
  private JwtUtil jwtUtil;
  
//  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  
  
  @Autowired
  private EmailService emailService;
  
  @Autowired
  private UserRepository userRepository;
  
  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
//    if (!signupRequest.getPassword().equals(signupRequest.getConfirmPassword())) {
//      return ResponseEntity.badRequest().body("Passwords do not match");
//    }
    User user = new User();
    user.setName(signupRequest.getName());
    user.setEmail(signupRequest.getEmail());
    user.setPassword(signupRequest.getPassword());
    user.setRole("ROLE_USER");
    User createdUser = userService.signup(user);
    
    emailService.sendWelcomeEmail(createdUser.getEmail(), createdUser.getName());
    
    return ResponseEntity.ok(createdUser);
  }
  
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
      try {
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
          );
      } catch (BadCredentialsException e) {
          return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Incorrect email or password"));
      }

      final UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
      final String jwt = jwtUtil.generateToken(
          userDetails.getUsername(),
          userDetails.getAuthorities().iterator().next().getAuthority()
      );

      // Fetch user details
      User user = userRepository.findByEmail(loginRequest.getEmail())
          .orElseThrow(() -> new RuntimeException("User not found"));

      // Create response object
      Map<String, Object> response = new HashMap<>();
      response.put("token", jwt);
      response.put("user", new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole()));

      return ResponseEntity.ok(response);
  }

  
  @PostMapping("/forgot-password")
  public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
      User user = userService.findByEmail(forgotPasswordRequest.getEmail());
      if (user == null) {
          return ResponseEntity.badRequest().body("User with this email does not exist");
      }

      String resetLink = "http://yourfrontend/reset-password?email=" + user.getEmail();
      String subject = "🔒 Reset Your Password - VenueHub";

      // HTML Email Body
      String htmlContent = "<div style='font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f4;'>"
              + "<div style='max-width: 600px; margin: auto; background: white; padding: 20px; border-radius: 10px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);'>"
              + "<h2 style='color: #333; text-align: center;'>🔒 Reset Your Password</h2>"
              + "<p>Hi <b>" + user.getName() + "</b>,</p>"
              + "<p>You requested to reset your password. Here is your current password:</p>"
              + "<div style='background: #eee; padding: 10px; text-align: center; font-size: 18px; border-radius: 5px;'>"
              + "<b>" + user.getPassword() + "</b></div>"
              + "<p>For security reasons, we recommend that you reset your password immediately.</p>"
              + "<p>Click the button below to reset your password:</p>"
              + "<div style='text-align: center;'>"
              + "<a href='" + resetLink + "' style='background: #007bff; color: white; padding: 10px 20px; text-decoration: none; border-radius: 5px; display: inline-block;'>"
              + "Reset Password</a></div>"
              + "<p>If you did not request this, you can ignore this email.</p>"
              + "<hr style='margin: 20px 0;'>"
              + "<p style='text-align: center; font-size: 12px; color: #666;'>© 2025 VenueHub. All rights reserved.</p>"
              + "</div></div>";

      emailService.sendHtmlEmail(user.getEmail(), subject, htmlContent);
      
      return ResponseEntity.ok("Password reset instructions sent to your email");
  }

}
