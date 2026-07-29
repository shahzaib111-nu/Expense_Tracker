package com.example.expensetracker.Service;

import com.example.expensetracker.DTO.Response.EmailDetails;

public interface EmailService {

    // Method to send simple email
    String sendSimpleMail(EmailDetails details);

    // Method to send email with attachment
    String sendMailWithAttachment(EmailDetails details);
}
