package com.entelgy.backend.challenge.email.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    private String id;
    private String postId;
    private String name;
    private String email;
    private String body;
}
