package com.rest.api.controller;

import com.rest.api.entity.Employ;
import com.rest.api.entity.Member;
import com.rest.api.repo.EmployJpaRepo;
import com.rest.api.repo.MemberJpaRepo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Slf4j
@Controller
public class HelloController {

    private static final String HELLO = "helloworld-nice to meet you";

    private final EmployJpaRepo employJpaRepo;
    private final MemberJpaRepo memberJpaRepo;

    @Setter
    @Getter
    public static class Hello {
        private String message;
    }

    @GetMapping(value = "/helloworld/string")
    @ResponseBody
    public Employ helloworldString() {

        Employ employ = Employ.builder().employee(memberJpaRepo.findById(1L).get()).build();
        employ = employJpaRepo.save(employ);
        log.debug("Helloworld");
        log.info("Helloworld");
        return employ;
    }

    @GetMapping(value = "/helloworld/json")
    @ResponseBody
    public Employ helloworldJson() {
        return employJpaRepo.findById(2L).get();
    }

    @GetMapping(value = "/helloworld/page")
    public String helloworld() {
        memberJpaRepo.save(Member.builder().email("happydaddy@naver.com").name("happydaddy").build());
        memberJpaRepo.save(Member.builder().email("angrydaddy@naver.com").name("angrydaddy").build());
        return "true";
//        return HELLO;
    }

    @GetMapping("/helloworld/long-process")
    @ResponseBody
    public String pause() throws InterruptedException {
        Thread.sleep(10000);
        return "Process finished";
    }
}
