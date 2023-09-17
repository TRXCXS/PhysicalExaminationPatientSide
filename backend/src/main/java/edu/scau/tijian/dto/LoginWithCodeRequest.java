package edu.scau.tijian.dto;

import lombok.Data;

@Data
public class LoginWithCodeRequest {
    private String phoneNumber;
    private Integer code;
}
