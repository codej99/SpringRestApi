package com.rest.api.controller;

import com.rest.api.entity.User;
import com.rest.api.repo.UserJpaRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserJpaRepo userJpaRepo;

    @ApiOperation(value = "회원 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "/user")
    public List<User> findAllUser() {
        return userJpaRepo.findAll();
    }

    @ApiOperation(value = "회원 입력", notes = "모든 회원을 조회한다")
    @PostMapping(value = "/user")
    public User save(@ApiParam(value = "회원이름", required = true) @RequestParam String name,
                     @ApiParam(value = "회원이메일", required = true) @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return userJpaRepo.save(user);
    }
}
