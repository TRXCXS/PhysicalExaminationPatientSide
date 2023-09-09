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

    @Override
    public String toString() {
        return userId + '\n' +
                password + '\n' +
                realName + '\n' +
                Integer.toString(sex) + '\n' +
                identityCard + '\n' +
                birthday + '\n' +
                Integer.toString(userType);
    }
}
