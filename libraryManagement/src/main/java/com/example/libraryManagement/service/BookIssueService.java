package com.example.libraryManagement.service;

import com.example.libraryManagement.entity.Book;
import com.example.libraryManagement.entity.BookIssue;
import com.example.libraryManagement.entity.Member;
import com.example.libraryManagement.exception.BookAlreadyIssuedException;
import com.example.libraryManagement.exception.BookNotFoundException;
import com.example.libraryManagement.repository.BookIssueRepository;
import com.example.libraryManagement.repository.BookRepository;
import com.example.libraryManagement.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class BookIssueService {

    @Autowired
    private BookIssueRepository bookIssueRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    public BookIssue issueBook(Long bookId,Long memberId){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        if (!book.isAvailable()){
            throw new BookAlreadyIssuedException(book.getId());
        }
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));

        BookIssue issue = new BookIssue();
        issue.setBook(book);
        issue.setMember(member);
        issue.setIssueDate(LocalDate.now());
        issue.setReturned(false);

        book.setAvailable(false);
        bookRepository.save(book);

        return bookIssueRepository.save(issue);
    }

    public BookIssue returnBook(Long issueId){
        BookIssue issue = bookIssueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue record not found with id: " + issueId));
        issue.setReturned(true);
        issue.setReturnDate(LocalDate.now());

        Book book = issue.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return bookIssueRepository.save(issue);
    }
    public List<BookIssue> getIssuesByMember(Long memberId){
        return bookIssueRepository.findByMemberId(memberId);
    }
}
