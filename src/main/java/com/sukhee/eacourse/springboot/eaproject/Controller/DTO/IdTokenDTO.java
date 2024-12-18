package com.sukhee.eacourse.springboot.eaproject.Controller.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IdTokenDTO {
    private String token;

    public IdTokenDTO(String token) {
        this.token = token;
    }

}