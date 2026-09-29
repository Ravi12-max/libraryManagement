package com.example.libraryManagement.service;

import com.example.libraryManagement.entity.Member;
import com.example.libraryManagement.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;
    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }
    public Member addMember(Member member){
        return memberRepository.save(member);
    }
}
