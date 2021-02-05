package com.entelgy.backend.challenge.email.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EmailDto {
    private String id;
    private String postId;
    private String name;
    private String email;
    private String body;
}
