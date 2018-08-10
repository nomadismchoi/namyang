package com.cafepay.project.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer no;

    private String id;

    @JsonIgnore
    private String password;

    private String name;

    private String birth;

    private gender gender;

    private int point;

    @JsonIgnore
    private LocalDateTime createAt;
    @JsonIgnore
    private LocalDateTime updateAt;

    public enum gender{
        M,F
    }


}
