package com.example.libraryManagement.controller;

import com.example.libraryManagement.entity.Member;
import com.example.libraryManagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }

    @PostMapping
    public Member addMember(@RequestBody Member member){
        return memberService.addMember(member);
    }
}
