package edu.scau.tijian.service;

import edu.scau.tijian.dto.LoginWithCodeRequest;
import edu.scau.tijian.pojo.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User login(User user) throws Exception;

    User getUserByUserId(User user);

    Integer saveUser(User user) throws Exception;

    User loginWithCode(LoginWithCodeRequest loginWithCodeRequest);
}
