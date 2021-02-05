package com.entelgy.backend.challenge.email.service;

import com.entelgy.backend.challenge.email.dto.EmailDto;
import com.entelgy.backend.challenge.email.model.Email;

import java.util.List;

public interface EmailService {
    public Email saveEmail(EmailDto emailDto);
    public Email getEmailById(String id) throws Exception;
    public List<Email> getEmailsByPostId(String id);
    public List<Email> getAll();
}
