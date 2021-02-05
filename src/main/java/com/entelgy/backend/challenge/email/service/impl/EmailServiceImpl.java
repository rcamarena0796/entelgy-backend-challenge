package com.entelgy.backend.challenge.email.service.impl;

import com.entelgy.backend.challenge.email.dto.EmailDto;
import com.entelgy.backend.challenge.email.model.Email;
import com.entelgy.backend.challenge.email.repository.EmailRepository;
import com.entelgy.backend.challenge.email.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private EmailRepository emailRepository;

    @Override
    public Email saveEmail(EmailDto emailDto) {
        Email email = Email.builder().id(emailDto.getId()).email(emailDto.getEmail()).postId(emailDto.getPostId()).build();
        emailRepository.save(email);
        return email;
    }

    @Override
    public Email getEmailById(String id) throws Exception{
        return emailRepository.findById(id).orElseThrow(() -> new Exception("Email not found for this id :: " + id));
    }

    @Override
    public List<Email> getEmailsByPostId(String id){
        return emailRepository.findAllByPostId(id);
    }

    @Override
    public List<Email> getAll(){
        return emailRepository.findAll();
    }

}
