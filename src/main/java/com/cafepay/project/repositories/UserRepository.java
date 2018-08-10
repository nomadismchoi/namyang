package com.cafepay.project.repositories;

import com.cafepay.project.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User , Integer> {
    User findByIdAndPassword(String id, String password);

    User findById(String id);
}
