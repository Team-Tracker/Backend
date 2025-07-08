package io.github.teamtracker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.teamtracker.model.team.Member;
import io.github.teamtracker.repository.MemberRepository;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Iterable<Member> getAllMembers() {
        return this.memberRepository.findAll();
    }

    public Iterable<Member> getMembers(Integer teamId) {
        Iterable<Member> members = this.memberRepository.findByTeamId(teamId);

        return members;
    }
}