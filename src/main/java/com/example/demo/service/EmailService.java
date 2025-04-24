package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
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

          helper.setFrom("venuehub.com@gmail.com"); // Update with your sender email
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
      return "
        <div style="font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background-color: #f4f6f8; padding: 40px;">
  <div style="max-width: 600px; margin: 0 auto; background: #ffffff; border-radius: 12px; box-shadow: 0 8px 24px rgba(0,0,0,0.08); overflow: hidden;">
    <div style="padding: 30px; text-align: center;">
    
      <h2 style="color: #333333; font-size: 26px; margin-bottom: 10px;">Thank You for Registering, <span style="color: #007BFF;">userName</span>!</h2>
      <p style="font-size: 16px; color: #555555; line-height: 1.6;">
        Welcome to <strong style="color: #007BFF;">VenueHub</strong>. We’re excited to have you on board and can’t wait for you to start exploring our platform.
      </p>
      <a href="https://your-website.com" 
         style="display: inline-block; margin-top: 25px; padding: 12px 30px; font-size: 16px; color: #ffffff; background-color: #007BFF; 
                text-decoration: none; border-radius: 8px; font-weight: 600; box-shadow: 0 4px 12px rgba(0,123,255,0.3);">
        Visit VenueHub
      </a>
      <p style="margin-top: 35px; font-size: 15px; color: #777777;">
        Happy Booking!<br><strong style="color: #007BFF;">VenueHub Team</strong>
      </p>
    </div>
  </div>
</div>
          ";
  }
}
