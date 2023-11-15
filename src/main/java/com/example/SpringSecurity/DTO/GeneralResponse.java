/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.SpringSecurity.DTO;

import com.example.SpringSecurity.Exception.HttpStatusConstant;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GeneralResponse<T> implements Serializable {
    private String code;
    private String message;
    private T data;

    public static GeneralResponse<?> ok(Object data) {
        return GeneralResponse.builder().code(HttpStatusConstant.SUCCESS_CODE).message(HttpStatusConstant.SUCCESS_MESSAGE).data(data).build();
    }

    public static GeneralResponse<?> error(String code, String message) {
        return GeneralResponse.builder().code(code).message(message).build();
    }

    public static GeneralResponse<?> buildAll(String code, String message, Object data) {
        return GeneralResponse.builder().code(code).message(message).data(data).build();
    }
    
}
