package com.example.libraryManagement.controller;

import com.example.libraryManagement.entity.BookIssue;
import com.example.libraryManagement.service.BookIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class BookIssueController {
    @Autowired
    private BookIssueService bookIssueService;

    @PostMapping("/issue")
    public BookIssue issueBook(@RequestParam Long bookId,@RequestParam Long memberId){
        return bookIssueService.issueBook(bookId,memberId);
    }

    @PutMapping("/return/{issueId}")
    public BookIssue returnBook(@PathVariable Long issueId){
        return bookIssueService.returnBook(issueId);
    }
    @GetMapping("/member/{memberId}")
    public List<BookIssue> getIssuesByMember(@PathVariable Long memberId){
        return bookIssueService.getIssuesByMember(memberId);
    }
}
