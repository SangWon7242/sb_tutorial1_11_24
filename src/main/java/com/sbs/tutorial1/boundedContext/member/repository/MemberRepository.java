package com.sbs.tutorial1.boundedContext.member.repository;

import com.sbs.tutorial1.boundedContext.member.dto.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberRepository {
  List<Member> members;

  public MemberRepository() {
    members = new ArrayList<>();

    members.add(new Member("user1", "1234"));
    members.add(new Member("abc", "123456"));
    members.add(new Member("test", "1222"));
    members.add(new Member("bbc", "1111"));
    members.add(new Member("love", "5555"));
    members.add(new Member("hello", "123478"));
    members.add(new Member("user2", "12596"));
    members.add(new Member("user3", "33333"));
    members.add(new Member("good", "456732"));
    members.add(new Member("like", "123444"));
  }


  public Member findByUserName(String username) {
    /*
    for(Member member : members) {
      if(member.getUsername().equals(username)) {
        return member;
      }
    }
    */

    return members.stream()
        .filter(member -> member.getUsername().equals(username))
        .findFirst()
        .orElse(null);
  }
}