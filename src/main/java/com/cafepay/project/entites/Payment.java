package com.cafepay.project.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    private String cafeName;

    private String cafeMenu;

    private String tumblerSerial;

    private Integer price;

    private String cafeLocation;

    @JsonIgnore
    private LocalDateTime createAt;
}
