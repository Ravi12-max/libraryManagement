package com.example.libraryManagement.service;

import com.example.libraryManagement.entity.Book;
import com.example.libraryManagement.entity.BookIssue;
import com.example.libraryManagement.entity.Member;
import com.example.libraryManagement.exception.BookAlreadyIssuedException;
import com.example.libraryManagement.repository.BookIssueRepository;
import com.example.libraryManagement.repository.BookRepository;
import com.example.libraryManagement.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookIssueServiceTest {


    @Mock
    private BookIssueRepository bookIssueRepository;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private BookIssueService bookIssueService;

    @Test
    void issueBook_whenAvailable_marksBookUnavailableAndCreatesIssue(){
        Book book = new Book();
        book.setId(1L);
        book.setAvailable(true);
        Member member = new Member();
        member.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        when(bookIssueRepository.save(any(BookIssue.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        BookIssue result = bookIssueService.issueBook(1L,1L);
        assertFalse(book.isAvailable());
        assertSame(book,result.getBook());
        assertSame(member,result.getMember());
        assertFalse(result.isReturned());
        assertNotNull(result.getIssueDate());
        verify(bookRepository).save(book);
    }

    @Test
    void issueBook_whenAlreadyIssued_throwsAndSavesNothing() {
        Book book = new Book();
        book.setId(1L);
        book.setAvailable(false);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        assertThrows(BookAlreadyIssuedException.class, () -> bookIssueService.issueBook(1L, 1L));
        verify(bookIssueRepository, never()).save(any());
    }

    @Test
    void returnBook_marksReturnedAndBookAvailableAgain(){
        Book book = new Book();
        book.setId(1L);
        book.setAvailable(false);
        BookIssue issue = new BookIssue();
        issue.setId(1L);
        issue.setBook(book);
        when(bookIssueRepository.findById(1L)).thenReturn(Optional.of(issue));
        when(bookIssueRepository.save(any(BookIssue.class)))
                .thenAnswer(inv -> inv.getArgument(0));

        BookIssue result = bookIssueService.returnBook(1L);

        assertTrue(result.isReturned());
        assertNotNull(result.getReturnDate());
        assertTrue(book.isAvailable());
    }
}
