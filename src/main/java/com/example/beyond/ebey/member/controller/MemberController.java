package com.example.beyond.ebey.member.controller;

import com.example.beyond.ebey.member.domain.Member;
import com.example.beyond.ebey.member.dto.MemberSaveReqDto;
import com.example.beyond.ebey.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member/save")
    public ResponseEntity<Member> createMember(@RequestBody MemberSaveReqDto dto) {
        Member createdMember = memberService.createMember(dto);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }
}