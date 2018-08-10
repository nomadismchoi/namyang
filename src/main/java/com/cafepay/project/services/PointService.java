package com.cafepay.project.services;

import com.cafepay.project.configs.CustomException;
import com.cafepay.project.entites.User;
import com.cafepay.project.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 포인트를 충전한다.
     * @param userId
     * @param chargePoint
     * @return
     */
    public User chargePoint(String userId, Integer chargePoint){
        User user = userRepository.findById(userId);
        user.setPoint(user.getPoint() + chargePoint);
        userRepository.flush();
        return user;
    }

    /**
     * 포인트를 차감한다.
     * @param userId
     * @param deductPoint
     * @return
     */
    public User deductPoint(String userId, Integer deductPoint) throws CustomException{
        User user = userRepository.findById(userId);
        user.setPoint(user.getPoint() - deductPoint);

        if(user.getPoint() < 0){
            throw new CustomException("lackPointError" , "포인트가 부족합니다");
        }
        userRepository.flush();
        return user;
    }
}
