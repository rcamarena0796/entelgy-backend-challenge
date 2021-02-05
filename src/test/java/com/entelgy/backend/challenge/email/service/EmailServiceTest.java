package com.entelgy.backend.challenge.email.service;

import com.entelgy.backend.challenge.email.dto.EmailDto;
import com.entelgy.backend.challenge.email.model.Email;
import com.entelgy.backend.challenge.email.repository.EmailRepository;
import com.entelgy.backend.challenge.email.service.impl.EmailServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
public class EmailServiceTest {
    @Mock
    private EmailRepository emailRepository;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private Email email;

    @Mock
    private EmailDto emailDto;

    @Mock
    private List<Email> emailList;

    @Mock
    private List<Email> emptyEmailList;


    @Before
    public void setUp() {
        this.emailList = new ArrayList<>();
        this.emptyEmailList = new ArrayList<>();
        this.emailList.add(new Email("1", "2", "user1@gmail.com"));
        this.emailList.add(new Email("2", "2", "user2@gmail.com"));

        this.email = Email.builder().id("12").email("aaa.a").postId("2").build();

        this.emailDto = EmailDto.builder().id("12").email("aaa.a").postId("2").name("aa").body("aaa").build();
    }

    @Test
    public void getEmailByIdShouldReturnOk() throws Exception {
        when(emailRepository.findById(Mockito.anyString())).thenReturn(Optional.of(email));
        Email response = emailService.getEmailById(Mockito.anyString());

        assertEquals(response.getId(), email.getId());
        assertEquals(response.getPostId(), email.getPostId());
        assertEquals(response.getEmail(), email.getEmail());

    }

    @Test(expected = Exception.class)
    public void getEmailByIdShouldReturnError() throws Exception {
        when(emailRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        emailService.getEmailById(Mockito.anyString());
    }

    @Test
    public void getAllShouldReturnOk() throws Exception {
        when(emailRepository.findAll()).thenReturn(emailList);
        List<Email> emails = emailService.getAll();

        assertEquals(emails.size(), emailList.size());

    }

    @Test
    public void getAllShouldReturnEmpty() throws Exception {
        when(emailRepository.findAll()).thenReturn(emptyEmailList);
        List<Email> emails = emailService.getAll();

        assertEquals(emails.size(), 0);
    }

    @Test
    public void getEmailsByPostIdShouldReturnOk() throws Exception {
        when(emailRepository.findAllByPostId(Mockito.anyString())).thenReturn(emailList);
        List<Email> emails = emailService.getEmailsByPostId(Mockito.anyString());

        assertEquals(emails.size(), emailList.size());
    }

    @Test
    public void getEmailsByPostIdShouldReturnEmpty() throws Exception {
        when(emailRepository.findAllByPostId(Mockito.anyString())).thenReturn(emptyEmailList);
        List<Email> emails = emailService.getEmailsByPostId(Mockito.anyString());

        assertEquals(emails.size(), emptyEmailList.size());
    }


    @Test
    public void createEmailShouldReturnOk() throws Exception {
        when(emailRepository.save(Mockito.any())).thenReturn(email);
        Email response = emailService.saveEmail(emailDto);

        assertEquals(response.getId(), email.getId());
        assertEquals(response.getPostId(), email.getPostId());
        assertEquals(response.getEmail(), email.getEmail());
    }

}