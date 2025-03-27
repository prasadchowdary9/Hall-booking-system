package com.example.demo.controller;

import com.example.demo.dto.ForgotPasswordRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.SignupRequest;
import com.example.demo.model.User;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
  
  @Autowired
  private EmailService emailService;
  
  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
    if (!signupRequest.getPassword().equals(signupRequest.getConfirmPassword())) {
      return ResponseEntity.badRequest().body("Passwords do not match");
    }
    User user = new User();
    user.setName(signupRequest.getName());
    user.setEmail(signupRequest.getEmail());
    user.setPassword(signupRequest.getPassword());
    user.setRole("ROLE_USER");
    User createdUser = userService.signup(user);
    return ResponseEntity.ok(createdUser);
  }
  
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
      );
    } catch (BadCredentialsException e) {
      return ResponseEntity.badRequest().body("Incorrect email or password");
    }
    final UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());
    final String jwt = jwtUtil.generateToken(userDetails.getUsername(),
      userDetails.getAuthorities().iterator().next().getAuthority());
    return ResponseEntity.ok(new JwtResponse(jwt));
  }
  
  @PostMapping("/forgot-password")
  public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest) {
    User user = userService.findByEmail(forgotPasswordRequest.getEmail());
    if (user == null) {
      return ResponseEntity.badRequest().body("User with this email does not exist");
    }
    String resetLink = "http://yourfrontend/reset-password?email=" + user.getEmail();
    String subject = "Password Reset Request";
    String text = "Hi " + user.getName() + ",\n\n"
                  + "Click the link below to reset your password:\n"
                  + resetLink + "\n\n"
                  + "If you did not request a password reset, please ignore this email.\n\n"
                  + "Regards,\nYour Company";
    emailService.sendEmail(user.getEmail(), subject, text);
    return ResponseEntity.ok("Password reset instructions sent to your email");
  }
}
