package com.cafepay.project.services;

import com.cafepay.project.configs.CustomException;
import com.cafepay.project.controllers.UserController;
import com.cafepay.project.entites.User;
import com.cafepay.project.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 유저서비스
 *
 * @author 최현진
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    /**
     * 유저정보 리스트를 조회한다.
     *
     * @return
     */
    public List<User> selectUserList() {
        return userRepository.findAll();
    }

    /**
     * 회원가입을 한다.
     * @param signUpUser
     * @throws CustomException
     */
    public void signUpUser(UserController.SignUpUser signUpUser) throws CustomException {
        if (signUpUser.getId().length() < 6) {
            throw new CustomException("DeniedIdLength", "아이디 길이를 6자 이상으로 해주십시오.");
        }

        if (signUpUser.getPassword().length() < 8) {
            throw new CustomException("DeniedPasswordLength", "비밀번호 길이를 8자 이상으로 해주십시오.");
        }

        createUserAccount(signUpUser);
    }

    /**
     * 회원가입 DTO를 엔티티에 등록하는 로직.
     * @param signUpUser
     */
    private void createUserAccount(UserController.SignUpUser signUpUser) {
        User newUser = new User();
        newUser.setId(signUpUser.getId());
        newUser.setPassword(signUpUser.getPassword());
        newUser.setBirth(signUpUser.getBirth());
        newUser.setGender(signUpUser.getGender());
        newUser.setName(signUpUser.getName());
        newUser.setPoint(0);
        newUser.setCreateAt(LocalDateTime.now());
        newUser.setUpdateAt(LocalDateTime.now());
        userRepository.saveAndFlush(newUser);
    }

    /**
     * 회원 로그인을 한다.
     *
     * @param loginUser
     * @throws CustomException
     */
    public void loginUser(UserController.LoginUser loginUser) throws CustomException {
        if (StringUtils.isEmpty(loginUser.getId()) || StringUtils.isEmpty(loginUser.getPassword())) {
            throw new CustomException("WrongAccess", "아이디 혹은 비밀번호를 입력해주십시오.");
        }

        User checkUser = userRepository.findByIdAndPassword(loginUser.getId(), loginUser.getPassword());

        if (checkUser == null) {
            throw new CustomException("WrongPassword", "아이디 혹은 비밀번호가 잘못되었습니다.");
        }
    }
}
