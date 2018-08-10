package com.cafepay.project.services;

import com.cafepay.project.configs.CustomException;
import com.cafepay.project.entites.Store;
import com.cafepay.project.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    /**
     * 매장 정보를 등록한다.
     * @param name
     * @param file
     * @throws Exception
     */
    public void registerStore(String name, MultipartFile file) throws Exception{

        Store findStore = storeRepository.findByName(name);

        if(findStore != null){
            throw new CustomException("alreadyExistStore" , "이미 존재하는 매장입니다.");
        }

        File tempFile = new File("/img","name");
        file.transferTo(tempFile);

        Store store = new Store();

        store.setLogo_url(tempFile.getPath());
        store.setName(name);
        store.setCreateAt(LocalDateTime.now());
        store.setUpdateAt(LocalDateTime.now());

        storeRepository.saveAndFlush(store);
    }

    /**
     * 매장 이름으로 정보를 조회한다.
     * @param name
     * @return
     */
    public Store getStoreByName(String name) {
        return storeRepository.findByName(name);
    }


    /**
     * 매장리스트를 조회한다.
     * @return
     */
    public List<Store> getStoreList() {
        return storeRepository.findAll();
    }
}
