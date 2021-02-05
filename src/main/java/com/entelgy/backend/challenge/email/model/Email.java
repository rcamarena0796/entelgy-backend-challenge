package com.entelgy.backend.challenge.email.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "EMAIL")
@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class Email {
    @Id
    private String id;
    private String postId;
    private String email;
}
