package com.entelgy.backend.challenge.email.expose.web;


import com.entelgy.backend.challenge.email.dto.EmailDto;
import com.entelgy.backend.challenge.email.model.Email;
import com.entelgy.backend.challenge.email.service.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmailController.class)
@RunWith(SpringRunner.class)
public class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmailService emailService;

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
    public void getEmailShouldReturnOk() throws Exception {
        when(emailService.getEmailById(Mockito.anyString())).thenReturn(email);
        this.mockMvc.perform(get("/api/email/12")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(mapToJson(email))));
    }

    @Test
    public void getEmailShouldReturnNotFound() throws Exception {
        when(emailService.getEmailById(Mockito.anyString())).thenThrow(new Exception(""));
        this.mockMvc.perform(get("/api/email/12")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void getAllShouldReturnOk() throws Exception {
        when(emailService.getAll()).thenReturn(emailList);
        this.mockMvc.perform(get("/api/email/findAll")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(mapToJson(emailList))));
    }

    @Test
    public void getAllShouldReturnEmpty() throws Exception {
        when(emailService.getAll()).thenReturn(emptyEmailList);
        this.mockMvc.perform(get("/api/email/findAll")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(mapToJson(emptyEmailList))));
    }

    @Test
    public void getAllEmailByPostIdShouldReturnOk() throws Exception {
        when(emailService.getEmailsByPostId(Mockito.anyString())).thenReturn(emailList);
        this.mockMvc.perform(get("/api/email/emailsByPostId/22")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(mapToJson(emailList))));
    }

    @Test
    public void getAllEmailByPostIdShouldReturnEmpty() throws Exception {
        when(emailService.getEmailsByPostId(Mockito.anyString())).thenReturn(emptyEmailList);
        this.mockMvc.perform(get("/api/email/emailsByPostId/22")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(mapToJson(emptyEmailList))));
    }


    @Test
    public void createEmailShouldReturnOk() throws Exception {
        when(emailService.saveEmail(Mockito.any())).thenReturn(email);
        this.mockMvc.perform(post("/api/email").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(emailDto))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(mapToJson(email))));
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

}
