package com.cafepay.project.repositories;

import com.cafepay.project.entites.Coupon;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {

    /**
     * 사용 가능한 쿠폰 리스트 조회
     * @param userId
     * @return
     */
    List<Coupon> findByUserIdAndValidationTrue(String userId);

    /**
     * 사용 가능한 쿠폰의 개수를 조회한다.
     * @param userId
     * @param cafeName
     * @return
     */
    Integer countByUserIdAndCafeNameAndValidationTrue(String userId, String cafeName);


    /**
     * 사용 가능한 쿠폰 목록들을 조회한다.
     * @param userId
     * @param cafeName
     * @param pageable
     * @return
     */
    List<Coupon> findByUserIdAndCafeNameAndValidationTrue (String userId, String cafeName, Pageable pageable);


}
