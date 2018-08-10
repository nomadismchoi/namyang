package com.cafepay.project.repositories;

import com.cafepay.project.entites.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Integer> {

    Store findByName(String name);
}
