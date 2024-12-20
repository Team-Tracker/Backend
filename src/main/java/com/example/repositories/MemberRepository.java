package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}