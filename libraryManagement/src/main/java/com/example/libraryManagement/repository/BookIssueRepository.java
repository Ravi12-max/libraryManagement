package com.example.libraryManagement.repository;

import com.example.libraryManagement.entity.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookIssueRepository extends JpaRepository<BookIssue,Long> {
    List<BookIssue> findByMemberId(Long memberId);
    List<BookIssue> findByBookId(Long bookId);
}
