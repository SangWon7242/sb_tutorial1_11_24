package com.sbs.tutorial1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
// 개발자가 스프링부트한테 말한다.
// "이 클래스는 웹 요청을 받아서 작업할 거야!"
// "그리고 해당 클래스는 컨트롤러야!"
public class HomeController {
  int num;

  public HomeController() {
    num = -1;
  }

  @GetMapping("/home/main")
  // 개발자가 스프링부트한테 말한다.
  // 만약에 `/home/main`이라는 요청이 들어오면 아래 메서드를 실행시켜줘
  @ResponseBody
  // 아래 메서드를 실행한 후 리턴값을 응답으로 삼아서
  // body에 출력해줘
  public String showHome() {
    return "어서오세요.";
  }

  @GetMapping("/home/main2")
  @ResponseBody
  public String showHome2() {
    return "환영합니다.";
  }

  @GetMapping("/home/main3")
  @ResponseBody
  public String showHome3() {
    return "스프링부트 획기적이다.";
  }

  @GetMapping("/home/increase")
  @ResponseBody
  public int showIncrease() {
    num++;
    return num;
  }

  @GetMapping("/home/plus")
  @ResponseBody
  public int showPlus(@RequestParam(defaultValue = "0") int a, @RequestParam int b) {
    return a + b;
  }

  @GetMapping("/home/returnBoolean")
  @ResponseBody
  public boolean showReturnBoolean() {
    return true;
  }

  @GetMapping("/home/returnDouble")
  @ResponseBody
  public Double showReturnDouble() {
    return Math.PI;
  }

  @GetMapping("/home/returnArray")
  @ResponseBody
  public int[] showReturnArray() {
    int[] arr = new int[]{10, 20, 30};

    return arr;
  }

  @GetMapping("/home/returnIntList")
  @ResponseBody
  public List<Integer> showReturnIntList() {
    List<Integer> list = new ArrayList<>(){{
      add(10);
      add(20);
      add(30);
    }};

    /*
    List<Integer> list = new ArrayList<>();
    list.add(10);
    list.add(20);
    list.add(30);
     */


    return list;
  }

  @GetMapping("/home/returnMap")
  @ResponseBody
  public Map<String, Object> showReturnMap() {
    Map<String, Object> map = new LinkedHashMap<>() {{
      put("id", 1);
      put("subject", "제목1");
      put("content", "내용1");
      put("writerName", "김철수");
      put("articleNo", new ArrayList<>() {{
        add(1);
        add(2);
        add(3);
      }});
    }};

    return map;
  }
}
