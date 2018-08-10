package com.cafepay.project.controllers;

import com.cafepay.project.configs.CustomException;
import com.cafepay.project.entites.User;
import com.cafepay.project.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping({"/users"})
@Api(tags = {"유저 서비스"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value ="유저 목록 조회")
    private List<User> selectUserList(){
        return userService.selectUserList();
    }

    @PostMapping
    @ApiOperation(value = "유저 회원가입")
    private void createUser(@RequestBody SignUpUser signUpUser) throws CustomException{
        userService.signUpUser(signUpUser);
    }

    @PostMapping("login")
    @ApiOperation(value = "유저 로그인")
    private void joinUser(@RequestBody LoginUser loginUser) throws CustomException {
        userService.loginUser(loginUser);
    }

    @Data
    public static class LoginUser{
        private String id;
        private String password;
    }

    @Data
    public static class SignUpUser{
        private String id;
        private String password;
        private String name;
        private User.gender gender;
        private String birth;
    }



}
