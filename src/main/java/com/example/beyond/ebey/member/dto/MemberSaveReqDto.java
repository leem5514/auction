package com.example.beyond.ebey.member.dto;

import com.example.beyond.ebey.member.domain.Member;
import com.example.beyond.ebey.member.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveReqDto {
    private String email;
    private String username;
    private String password;
    private Role role;

    public static Member toEntity(MemberSaveReqDto dto) {
        return Member.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
    }
}
