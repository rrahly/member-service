package com.hexagon.member_service.repository;

import com.hexagon.member_service.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, String> {

}
