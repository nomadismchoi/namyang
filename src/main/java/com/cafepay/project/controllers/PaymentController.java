package com.cafepay.project.controllers;

import com.cafepay.project.entites.Payment;
import com.cafepay.project.services.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@Api(tags = "결제 서비스")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    @ApiOperation(value = "결제하기")
    private void payment(
            @RequestBody Payment payment)
    {
        paymentService.registPayment(payment);
    }

    @GetMapping("{userId}")
    @ApiOperation(value = "결제 목록 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    private PaymentsList getPaymentList(
            @PathVariable String userId,
            @PageableDefault(size=20, sort="createAt",direction = Sort.Direction.ASC)Pageable pageable
            ){
        PaymentsList paymentsList = new PaymentsList();
        paymentsList.setPaymentCount(paymentService.getCountByuserId(userId));
        paymentsList.setPaymentList(paymentService.getListByuserId(userId, pageable));
        return paymentsList;
    }

    @GetMapping("recommend/{userId}")
    private List<Payment> getRecoomendByCafeMenu(
            @PathVariable String userId;
    ){
     paymentService.getRecommendByUserId(userId);
    }


    @Data
    private static class PaymentsList{
        private Integer paymentCount;
        private List<Payment> paymentList;
    }
}
