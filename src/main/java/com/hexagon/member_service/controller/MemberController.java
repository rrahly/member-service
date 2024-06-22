package com.hexagon.member_service.controller;


import com.hexagon.member_service.model.Member;
import com.hexagon.member_service.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<?> createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @GetMapping
    public ResponseEntity<?> fetchMembers() {
        return memberService.fetchMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchMemberById(@PathVariable String id) {
        return memberService.fetchMemberById(id);
    }
}
