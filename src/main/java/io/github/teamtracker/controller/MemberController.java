package io.github.teamtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.teamtracker.model.team.Member;
import io.github.teamtracker.service.MemberService;

@Controller
@RequestMapping(path = "/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public Iterable<Member> getAllMembers() {
        return this.memberService.getAllMembers();
    }

    @GetMapping("/members/{teamId}")
    public Iterable<Member> getTeams(@PathVariable Integer teamId) {
        return this.memberService.getMembers(teamId);
    }
}