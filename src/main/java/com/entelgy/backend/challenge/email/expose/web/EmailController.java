package com.entelgy.backend.challenge.email.expose.web;

import com.entelgy.backend.challenge.email.dto.EmailDto;
import com.entelgy.backend.challenge.email.model.Email;
import com.entelgy.backend.challenge.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("")
    public ResponseEntity<Email> createEmail(@RequestBody EmailDto emailDto) {
        return ResponseEntity.ok().body(emailService.saveEmail(emailDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Email> getEmail(@PathVariable("id") String id) {
        try {
            return ResponseEntity.ok().body(emailService.getEmailById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/emailsByPostId/{postId}")
    public ResponseEntity<List<Email>> getAllEmailByPostId(@PathVariable("postId") String postId) {

        return ResponseEntity.ok().body(emailService.getEmailsByPostId(postId));

    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Email>> getAll() {

        return ResponseEntity.ok().body(emailService.getAll());

    }
}
