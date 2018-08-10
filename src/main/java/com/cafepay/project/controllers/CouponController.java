package com.cafepay.project.controllers;

import com.cafepay.project.configs.CustomException;
import com.cafepay.project.entites.Coupon;
import com.cafepay.project.services.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@Api(tags = "쿠폰 서비스")
public class CouponController {

    @Autowired
    private CouponService couponService;


    @PostMapping("{userId}")
    @ApiOperation(value = "쿠폰 등록")
    private void registCoupon(@PathVariable String userId, @RequestBody String cafeName)
            throws CustomException
    { couponService.registerCoupon(userId,cafeName); }

    @GetMapping("{userId}/count")
    @ApiOperation(value = "쿠폰개수 조회")
    private CouponCountDTO getCouponCount(@PathVariable String userId)
    { return couponService.getCountByuserId(userId);}


    @GetMapping("{userId}")
    @ApiOperation(value = "쿠폰리스트 조회")
    private List<Coupon> getCouponList(@PathVariable String userId)
    { return couponService.getCouponList(userId);}

    @PutMapping("{userId}/{cafeName}")
    @ApiOperation(value = "쿠폰 사용")
    private void useCoupon(@PathVariable String userId, @PathVariable String cafeName) throws CustomException
    { couponService.useCoupon(userId,cafeName);}


    @Data
    public static class CouponCountDTO{

        private Integer starbucksCouponCount;

        private Integer tomntomsCouponCount;

        private Integer ediyaCouponCount;

        private Integer hollysCouponCount;

        private Integer angelinusCouponCount;
    }
}
