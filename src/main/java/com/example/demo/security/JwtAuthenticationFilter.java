package com.example.demo.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtUtil jwtUtil;
  
  @Autowired
  private CustomUserDetailsService customUserDetailsService;
  
  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    final String authorizationHeader = request.getHeader("Authorization");
    String email = null;
    String jwt = null;
    
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      jwt = authorizationHeader.substring(7);
      email = jwtUtil.extractUsername(jwt);
    }
    
    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
      if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
        UsernamePasswordAuthenticationToken authToken =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }

	/*
	 * @Override protected void
	 * doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
	 * jakarta.servlet.http.HttpServletResponse response,
	 * jakarta.servlet.FilterChain filterChain) throws
	 * jakarta.servlet.ServletException, IOException { // TODO Auto-generated method
	 * stub
	 * 
	 * }
	 */}
