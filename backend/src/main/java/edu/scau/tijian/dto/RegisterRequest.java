package edu.scau.tijian.dto;

import edu.scau.tijian.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private User user;
    private Integer code;
}
