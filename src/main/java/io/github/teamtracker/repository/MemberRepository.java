package io.github.teamtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.teamtracker.model.team.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}