package com.hexagon.member_service.service;

import com.hexagon.member_service.dto.MemberResponse;
import com.hexagon.member_service.dto.Project;
import com.hexagon.member_service.model.Member;
import com.hexagon.member_service.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public MemberService(MemberRepository memberRepository, RestTemplate restTemplate) {
        this.memberRepository = memberRepository;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> createMember(Member member) {
        try {
            return new ResponseEntity<Member>(memberRepository.save(member), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> fetchMembers() {
        List<Member> members = memberRepository.findAll();
        if (!members.isEmpty()) {
            return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Members", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> fetchMemberById(String id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            Project project = restTemplate.getForObject("http://localhost:8081/project/" + member.get().getProjectName(), Project.class);
            MemberResponse memberResponse = new MemberResponse(
                    member.get().getId(),
                    member.get().getName(),
                    member.get().getAge(),
                    project
            );
            return new ResponseEntity<>(memberResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No member found", HttpStatus.NOT_FOUND);
        }
    }
}
