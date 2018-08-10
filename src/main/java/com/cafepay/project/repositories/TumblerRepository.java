package com.cafepay.project.repositories;

import com.cafepay.project.entites.Tumbler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TumblerRepository extends JpaRepository<Tumbler, Integer> {
    List<Tumbler> findByUserId(String userID);
}
