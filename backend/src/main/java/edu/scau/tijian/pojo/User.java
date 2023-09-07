package edu.scau.tijian.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String userId;
    private String password;
    private String realName;
    private Integer sex;
    private String identityCard;
    private String birthday;
    private Integer userType;
}
