package com.cafepay.project.dtos;

import com.cafepay.project.entites.Coupon;
import com.cafepay.project.entites.User;
import lombok.Data;

import java.util.List;

@Data
public class UserByTumbler {
    private User user;
    private List<Coupon> coupon;
}
