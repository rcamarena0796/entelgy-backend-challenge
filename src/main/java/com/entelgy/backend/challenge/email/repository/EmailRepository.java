package com.entelgy.backend.challenge.email.repository;

import com.entelgy.backend.challenge.email.model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {
    List<Email> findAllByPostId(String postId);

}
