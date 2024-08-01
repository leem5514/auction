package com.example.beyond.ebey.member.service;

import com.example.beyond.ebey.member.domain.Member;
import com.example.beyond.ebey.member.dto.MemberSaveReqDto;
import com.example.beyond.ebey.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    @Autowired
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Member createMember(MemberSaveReqDto dto) {
        Member member = MemberSaveReqDto.toEntity(dto);
        return memberRepository.save(member);
    }


}

