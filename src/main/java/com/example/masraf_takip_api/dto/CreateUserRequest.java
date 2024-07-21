package com.example.masraf_takip_api.dto;

import com.example.masraf_takip_api.model.Role;
import lombok.Builder;

import java.util.Set;


@Builder
public record CreateUserRequest(
        String name,
        String username,
        String password,
        Set<Role> authorities
){
}
