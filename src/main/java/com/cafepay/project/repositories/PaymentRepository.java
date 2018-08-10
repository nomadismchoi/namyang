package com.cafepay.project.repositories;

import com.cafepay.project.entites.Payment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    Integer countByUserId(String userId);

    List<Payment> findByUserId(String userId, Pageable pageable);


    //TODO EVALUATE 평가 관련하여 컬럼 추가할 것
    //TODO USERID 및 EVALUATE 로 평점 조회
    List<Payment> findByUserIdAnd
}
