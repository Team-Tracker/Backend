package io.github.teamtracker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.teamtracker.repository.MemberRepository;
import io.github.teamtracker.repository.TeamRepository;

@Service
@Transactional
public class MemberService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public MemberService(TeamRepository teamRepository, MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
    }
}