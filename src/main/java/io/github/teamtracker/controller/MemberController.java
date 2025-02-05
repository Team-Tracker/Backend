package io.github.teamtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.teamtracker.repository.MemberRepository;

@Controller
@RequestMapping(path = "/member")
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;
}