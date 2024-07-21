package com.example.masraf_takip_api.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AuthRequest {
    private String username;
    private String password;
}

