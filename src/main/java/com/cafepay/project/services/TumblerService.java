package com.cafepay.project.services;

import com.cafepay.project.controllers.TumblerController;
import com.cafepay.project.entites.Tumbler;
import com.cafepay.project.repositories.TumblerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class TumblerService {

    @Autowired
    private TumblerRepository tumblerRepository;

    /**
     * 텀블러를 등록한다.
     * @param registerTumbler
     * @return
     */
    public void registerTumbler(TumblerController.RegisterTumbler registerTumbler){
        Tumbler tumbler = new Tumbler();
        tumbler.setUserId(registerTumbler.getUserId());
        tumbler.setSerial(registerTumbler.getSerial());
        tumbler.setCreateAt(LocalDateTime.now());
        tumbler.setUpdateAt(LocalDateTime.now());

        tumblerRepository.saveAndFlush(tumbler);
    }


    public List<Tumbler> getTumblerByUserId(String userId) {
        return tumblerRepository.findByUserId(userId);
    }
}
