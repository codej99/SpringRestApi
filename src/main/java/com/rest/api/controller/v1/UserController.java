package com.rest.api.controller.v1;

import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.entity.User;
import com.rest.api.model.response.BasicResult;
import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.repo.UserJpaRepo;
import com.rest.api.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService; // 결과를 처리할 Service

    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "/users")
    public ListResult<User> findAllUser() {
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(userJpaRepo.findAll());
    }

    @ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다")
    @GetMapping(value = "/user/{userId}")
    public BasicResult<User> findUserById(@ApiParam(value = "회원ID", required = true) @RequestParam int userId) {
        // 결과데이터가 단일건인경우 getBasicResult를 이용해서 결과를 출력한다.
//        return responseService.getBasicResult(userJpaRepo.findById(userId).orElse(null));
        return responseService.getBasicResult(userJpaRepo.findById(userId).orElseThrow(CUserNotFoundException::new));
    }

    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다")
    @PostMapping(value = "/user")
    public BasicResult<User> save(@ApiParam(value = "회원이름", required = true) @RequestParam String name,
                                  @ApiParam(value = "회원이메일", required = true) @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return responseService.getBasicResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = "/user")
    public BasicResult<User> modify(
            @ApiParam(value = "회원ID", required = true) @RequestParam int userId,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name,
            @ApiParam(value = "회원이메일", required = true) @RequestParam String email) {
        User user = new User();
        user.setId(userId);
        user.setName(name);
        user.setEmail(email);
        return responseService.getBasicResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
    @DeleteMapping(value = "/user/{userId}")
    public CommonResult delete(
            @ApiParam(value = "회원ID", required = true) @RequestParam int userId) {
        userJpaRepo.deleteById(userId);
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }
}
