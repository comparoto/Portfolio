package com.comparoto.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagesController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/projects")
    public String projects() {
        return "projects";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String sendEmail(@RequestParam("name") String name,
                            @RequestParam("email") String email,
                            @RequestParam("subject") String subject,
                            @RequestParam("message") String message, Model user) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo("julianacomparoto@gmail.com");
            mailMessage.setSubject("Portfolio - " + subject);
            mailMessage.setText("New message by:\n\n" +
                    "Name: " + name + "\n" +
                    "E-mail: " + email + "\n\n" +
                    "Message:\n" + message);

            mailSender.send(mailMessage);
            user.addAttribute("success", "Message sent successfully!");
        } catch (Exception e) {
            user.addAttribute("error", "Error sending the message. Please try again later.");
        }

        return "contact";
    }
}