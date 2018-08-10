package com.cafepay.project.configs;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@NoArgsConstructor
@Data
public class ExceptionData {

    private HashMap<String, Object> data;

    /**
     * 예외 발생 상세 데이터를 변환한다
     */

    public ExceptionData add(String key, Object value){
        if(this.data == null){
            this.data = new HashMap<>();
        }
        this.data.put(key,value);

        return this;
    }
}
