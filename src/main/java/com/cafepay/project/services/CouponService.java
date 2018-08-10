package com.cafepay.project.services;

import com.cafepay.project.configs.CustomException;
import com.cafepay.project.controllers.CouponController;
import com.cafepay.project.entites.Coupon;
import com.cafepay.project.entites.Store;
import com.cafepay.project.repositories.CouponRepository;
import com.cafepay.project.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private StoreRepository storeRepository;

    /**
     * 쿠폰을 등록한다.
     * @param userId
     * @param cafeName
     */
    public void registerCoupon(String userId, String cafeName) throws CustomException {

        Store store = storeRepository.findByName(cafeName);
        if(store == null) {
            throw new CustomException("dontExistStore","존재하지 않는 매장입니다.");
        }


        Coupon coupon = new Coupon();
        coupon.setUserId(userId);
        coupon.setCafeName(cafeName);
        coupon.setValidation(true);
        coupon.setCreateAt(LocalDateTime.now());
        coupon.setUpdateAt(LocalDateTime.now());

        couponRepository.saveAndFlush(coupon);
    }

    /**
     * 쿠폰 리스트를 조회한다.
     * @param userId
     * @return
     */
    public List<Coupon> getCouponList(String userId) {
        return couponRepository.findByUserIdAndValidationTrue(userId);
    }


    /**
     * 쿠폰을 사용한다.
     * @param userId
     * @param cafeName
     * @throws CustomException
     */
    public void useCoupon(String userId, String cafeName) throws CustomException {
        int countCoupon = couponRepository.countByUserIdAndCafeNameAndValidationTrue(userId,cafeName);

        if(countCoupon < 10){
            throw new CustomException("couponCountError","쿠폰의 개수가 부족합니다.");
        }

        PageRequest pageRequest = new PageRequest(0,10,new Sort(Sort.Direction.DESC, "createAt"));
        List<Coupon> coupons = couponRepository.findByUserIdAndCafeNameAndValidationTrue(userId,cafeName,pageRequest);

        for (Coupon coupon : coupons){
            coupon.setValidation(false);
            coupon.setUpdateAt(LocalDateTime.now());
        }

        couponRepository.flush();
    }

    /**
     * 쿠폰 개수를 조회한다.
     * @param userId
     * @return
     */
    public CouponController.CouponCountDTO getCountByuserId(String userId) {
        CouponController.CouponCountDTO couponCountDTO = new CouponController.CouponCountDTO();
        couponCountDTO.setAngelinusCouponCount(couponRepository.countByUserIdAndCafeNameAndValidationTrue(userId,"hollys"));
        couponCountDTO.setEdiyaCouponCount(couponRepository.countByUserIdAndCafeNameAndValidationTrue(userId,"ediya"));
        couponCountDTO.setHollysCouponCount(couponRepository.countByUserIdAndCafeNameAndValidationTrue(userId,"tomntoms"));
        couponCountDTO.setStarbucksCouponCount(couponRepository.countByUserIdAndCafeNameAndValidationTrue(userId,"angelinus"));
        couponCountDTO.setTomntomsCouponCount(couponRepository.countByUserIdAndCafeNameAndValidationTrue(userId,"starbucks"));
        return couponCountDTO;
    }

}
