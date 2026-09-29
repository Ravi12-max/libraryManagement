package com.example.libraryManagement.repository;

import com.example.libraryManagement.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member,Long> {
}
