package edu.scau.tijian.service;

import edu.scau.tijian.mapper.UserMapper;
import edu.scau.tijian.pojo.User;
import edu.scau.tijian.service.UserService;
import edu.scau.tijian.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserMapper userMapper;

    @Override
    public User login(User user) throws Exception {
        user.setPassword(SHA256.encrypt(user.getPassword()));
        return userMapper.getUserByUserIdAndPassword(user);
    }

    @Override
    public User getUserByUserId(User user) {
        return userMapper.getUserByUserId(user);
    }

    @Override
    public Integer saveUser(User user) throws Exception {
        user.setPassword(SHA256.encrypt(user.getPassword()));
        return userMapper.saveUser(user);
    }
}
