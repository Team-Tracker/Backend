package io.github.teamtracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.teamtracker.model.team.Member;
import io.github.teamtracker.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public Iterable<Member> getAllMembers() {
        return this.memberService.getAllMembers();
    }

    @GetMapping("/{teamId}")
    public Iterable<Member> getMembersByTeamId(@PathVariable Integer teamId) {
        return this.memberService.getMembers(teamId);
    }
}