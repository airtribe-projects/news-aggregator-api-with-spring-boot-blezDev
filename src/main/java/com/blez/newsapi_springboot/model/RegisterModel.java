package com.blez.newsapi_springboot.model;

import jakarta.validation.constraints.Email;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class RegisterModel {
    private String username;
    private String password;
    @Email
    private String email;
}
