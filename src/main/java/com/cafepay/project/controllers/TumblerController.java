package com.cafepay.project.controllers;


import com.cafepay.project.entites.Tumbler;
import com.cafepay.project.services.TumblerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tumblers")
@Api(tags = "텀블러 서비스")
public class TumblerController {

    @Autowired
    private TumblerService tumblerService;

    @PostMapping
    @ApiOperation(value = "텀블러 시리얼넘버 등록")
    private void registTumbler(
            @RequestBody RegisterTumbler registerTumbler
    ){
        tumblerService.registerTumbler(registerTumbler);
    }

    @GetMapping("{userId}")
    private List<Tumbler> getTumberById(
            @PathVariable String userId
    ){
        return tumblerService.getTumblerByUserId(userId);
    }



    @Data
    public static class RegisterTumbler{
        private String userId;
        private String serial;
    }
}
