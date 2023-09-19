package edu.scau.tijian.service;

import edu.scau.tijian.dto.LoginWithCodeRequest;
import edu.scau.tijian.dto.RegisterRequest;
import edu.scau.tijian.entity.PhoneCodePair;
import edu.scau.tijian.mapper.UserMapper;
import edu.scau.tijian.pojo.User;
import edu.scau.tijian.repository.PhoneCodePairRepository;
import edu.scau.tijian.service.UserService;
import edu.scau.tijian.utils.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final PhoneCodePairRepository phoneCodePairRepository;

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

    @Override
    public User loginWithCode(LoginWithCodeRequest loginWithCodeRequest) {
        String phone = loginWithCodeRequest.getPhoneNumber();
        boolean codeExists = phoneCodePairRepository.findById(phone).isPresent();
        if (codeExists) {
            PhoneCodePair p = phoneCodePairRepository.findById(phone).get();
            if (Objects.equals(p.getCode(), loginWithCodeRequest.getCode())) {
                phoneCodePairRepository.deleteById(phone);
                return userMapper.getUserByUserId(new User(phone));
            }
        }
        return null;
    }

    @Override
    public Integer registerWithCode(RegisterRequest registerRequest) throws Exception {
        User user = registerRequest.getUser();
        String phone = registerRequest.getUser().getUserId();
        boolean codeExists = phoneCodePairRepository.findById(phone).isPresent();
        if (!codeExists) return 0;
        PhoneCodePair p = phoneCodePairRepository.findById(phone).get();
        if (!Objects.equals(p.getCode(), registerRequest.getCode())) return 0;
        phoneCodePairRepository.deleteById(phone);
        user.setPassword(SHA256.encrypt(user.getPassword()));
        return userMapper.saveUser(user);
    }
}
