package com.cafepay.project.services;

import com.cafepay.project.entites.Payment;
import com.cafepay.project.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * 결제한다.
     * @param payment
     */
    public void registPayment(Payment payment) {
        payment.setCreateAt(LocalDateTime.now());
        paymentRepository.saveAndFlush(payment);
    }

    /**
     * 유저 결제목록 개수를 조회한다.
     * @param userId
     * @return
     */
    public Integer getCountByuserId(String userId) {
        return paymentRepository.countByUserId(userId);
    }


    /**
     * 유저 아이디로 결제 목록을 조회한다.
     * @param userId
     * @return
     */
    public List<Payment> getListByuserId(String userId, Pageable pageable) {
        return paymentRepository.findByUserId(userId,pageable);
    }

    /**
     * 추천 결제목록을 조회한다
     * 관련 코드는 레거시 보여주기 용일 것.
     * @param userId
     * @return
     */
    public List<Payment> getRecommendByUserId(String userId) {
        // TODO 아이디 및 평점조회로 서비스한 결제 메뉴로 한 번더 다른 사람들 메뉴 조회

        // TODO 카운팅 해서 다른 메뉴 가장 높은 것들을 순서대로 해서 뿌려줄 것 .

        return
    }

    //TODO 결제 목록에서 추천하는 서비스를 만든다.


}
