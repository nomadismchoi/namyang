package com.cafepay.project.configs;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomException extends Exception {

    private final String code;
    private final HashMap data;

    /**
     * 익셉션 코드를 리턴한다.
     *
     * @param code
     * @param message
     */
    public CustomException(String code, String message) {

        super(message);
        this.code = code;
        this.data = null;
    }

    /**
     * 익셉션 코드와 상세메세지를 리턴한다.
     *
     * @param code
     * @param message
     * @param data
     */
    public CustomException(String code, String message, ExceptionData data) {

        super(message);
        this.code = code;
        this.data = data.getData();
    }
}