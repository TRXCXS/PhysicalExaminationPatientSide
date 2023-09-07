package edu.scau.tijian.service;

import edu.scau.tijian.mapper.UserMapper;
import edu.scau.tijian.pojo.User;
import edu.scau.tijian.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.getUserByUserIdAndPassword(user);
    }

    @Override
    public User getUserByUserId(User user) {
        return userMapper.getUserByUserId(user);
    }

    @Override
    public Integer saveUser(User user) {
        return userMapper.saveUser(user);
    }
}
