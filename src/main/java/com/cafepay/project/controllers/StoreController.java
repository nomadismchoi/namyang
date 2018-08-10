package com.cafepay.project.controllers;


import com.cafepay.project.entites.Store;
import com.cafepay.project.services.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stores")
@Api(tags = "매장 서비스")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @GetMapping
    @ApiOperation(value = "매장 리스트 조회")
    private List<Store> getStoreList(){
        return storeService.getStoreList();
    }

    @GetMapping("{name}")
    @ApiOperation(value = "매장 정보 조회")
    private Store getStroeByName(
            @PathVariable String name
    ){
        return storeService.getStoreByName(name);
    }

}
