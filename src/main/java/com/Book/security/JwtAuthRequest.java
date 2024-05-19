package com.Book.security;

import lombok.Data;

@Data
public class JwtAuthRequest {

    private String username;//email is my username
    private String password;
}
