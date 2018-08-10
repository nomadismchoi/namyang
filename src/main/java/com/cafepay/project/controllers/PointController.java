package com.cafepay.project.controllers;

import com.cafepay.project.configs.CustomException;
import com.cafepay.project.entites.User;
import com.cafepay.project.services.PointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/points")
@Slf4j
@Api(tags = {"포인트 서비스"})
public class PointController {

    @Autowired
    private PointService pointService;

    @PutMapping("{userId}/charge")
    @ApiOperation(value = "포인트 충전")
    private User chargePoint(
            @PathVariable String userId,
            @RequestBody Integer chargePoint)
    { return pointService.chargePoint(userId, chargePoint); }

    @PutMapping("{userId}/deduct")
    @ApiOperation(value = "포인트 차감")
    private User deductPoint(
            @PathVariable String userId,
            @RequestBody Integer deductPoint) throws CustomException
    { return pointService.deductPoint(userId, deductPoint); }



}
