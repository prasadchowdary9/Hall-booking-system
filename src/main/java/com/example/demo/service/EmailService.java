package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;
  
  
  public void sendHtmlEmail(String to, String subject, String htmlContent) {
      try {
          MimeMessage message = mailSender.createMimeMessage();
          MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

          helper.setFrom("prasadchowdary983@gmail.com"); // Update with your sender email
          helper.setTo(to);
          helper.setSubject(subject);
          helper.setText(htmlContent, true); // Set true for HTML content

          mailSender.send(message);
      } catch (MessagingException e) {
          throw new RuntimeException("Failed to send email", e);
      }
  }
  public void sendWelcomeEmail(String toEmail, String userName) {
      String subject = "Welcome to VenueHub!";
      String htmlContent = buildWelcomeEmail(userName);

      try {
          MimeMessage message = mailSender.createMimeMessage();
          MimeMessageHelper helper = new MimeMessageHelper(message, true);

          helper.setTo(toEmail);
          helper.setSubject(subject);
          helper.setText(htmlContent, true);

          mailSender.send(message);
      } catch (MessagingException e) {
          e.printStackTrace();
      }
  }

  private String buildWelcomeEmail(String userName) {
      return "<div style='font-family: Arial, sans-serif; text-align: center; padding: 20px;'>"
              + "<h2 style='color: #333;'>Thank You for Registering, " + userName + "!</h2>"
              + "<p>Welcome to <span style='font-weight: bold; color: #007BFF;'>VenueHub</span>. We’re excited to have you on board!</p>"
              + "<a href='https://your-website.com' style='display: inline-block; margin-top: 15px; padding: 10px 20px; font-size: 16px; "
              + "color: white; background-color: #007BFF; text-decoration: none; border-radius: 5px;'>Visit VenueHub</a>"
              + "<p style='margin-top: 20px;'>Happy Booking!<br><strong>VenueHub Team</strong></p>"
              + "<img src='https://your-logo-url.com/logo.png' alt='VenueHub Logo' style='width: 150px; margin-top: 10px;'>"
              + "</div>";
  }
}
