package com.sbs.tutorial1.boundedContext.member.controller;

import com.sbs.tutorial1.base.rq.Rq;
import com.sbs.tutorial1.base.rsData.RsData;
import com.sbs.tutorial1.boundedContext.member.dto.Member;
import com.sbs.tutorial1.boundedContext.member.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@AllArgsConstructor
@Controller
public class MemberController {
  private final MemberService memberService;
  private final Rq rq;

  @GetMapping("/member/login")
  @ResponseBody
  public String showLogin() {
    // """ -> 텍스트 블록 문법
    // 멀티라인으로 문자열을 정의
    if(rq.isLogined()) {
      return """
             <h1>이미 로그인 되어있습니다.<h1>
             """.stripIndent();
    }
  
    // /member/doLogin -> doLogin만 작성 가능
    // /member/doLogin -> 절대경로
    // doLogin -> 상대 경로(현재 페이지의 위치를 기준으로 파일 참조)
    return """
          <h1>로그인</h1>
          <form action="doLogin">
            <input type="text" placeholder="아이디" name="username">
            <input type="password" placeholder="비밀번호" name="password">
            <input type="submit" value="로그인">
          </form>
          """;
  }

  @GetMapping("/member/doLogin")
  @ResponseBody
  public RsData login(String username, String password) {
    if(username == null || username.trim().isEmpty()) {
      return RsData.of("F-3", "username(을)를 입력해주세요.");
    }

    if(password == null || password.trim().isEmpty()) {
      return RsData.of("F-4", "비밀번호를 입력해주세요.");
    }

    RsData rsData = memberService.tryLogin(username, password);

    if(rsData.isSuccess()) {
      Member member = (Member) rsData.getData();
      rq.setSession("loginedMemberId", member.getId());
    }

    return rsData;
  }

  @GetMapping("/member/logout")
  @ResponseBody
  public RsData logout() {
    boolean cookieRemoved = rq.removeSession("loginedMemberId");

    if(!cookieRemoved) {
      return RsData.of("F-1", "이미 로그아웃 상태입니다.");
    }

    return RsData.of("S-1", "로그아웃 되었습니다.");
  }

  @GetMapping("/member/me")
  @ResponseBody
  public RsData showMe() {
    long loginedMemberId = rq.getSessionAsLong("loginedMemberId", 0);

    boolean isLogined = loginedMemberId > 0;

    if(!isLogined) {
      return RsData.of("F-1", "로그인 후 이용해주세요.");
    }

    Member member = memberService.findById(loginedMemberId);

    return RsData.of("S-1", "당신의 username(은)는 %s 입니다.".formatted(member.getUsername()));
  }

  @GetMapping("/member/session")
  @ResponseBody
  public String showSession() {
    return rq.getSessionDebugInfo().replaceAll("\n", "<br>");
  }
}
