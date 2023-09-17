package edu.scau.tijian.controller;

import edu.scau.tijian.dto.LoginWithCodeRequest;
import edu.scau.tijian.pojo.User;
import edu.scau.tijian.service.AliyunSmsService;
import edu.scau.tijian.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final AliyunSmsService aliyunSmsService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) throws Exception {
        return userService.login(user);
    }

    @PostMapping("/user-exists")
    public String userExists(@RequestBody User user) {
        User u = userService.getUserByUserId(user);
        return u == null ? "NO" : "YES";
    }

    @PostMapping("/register")
    public String saveUser(@RequestBody User user) throws Exception {
        return userService.saveUser(user) == 1 ? "Success" : "Failure";
    }

    @GetMapping("/sendCode")
    public String sendCode(@RequestParam String phoneNumber) throws ExecutionException, InterruptedException {
        return aliyunSmsService.sendAndSaveSmsCode(phoneNumber);
    }

    @PostMapping("/loginWithCode")
    public User loginWithCode(@RequestBody LoginWithCodeRequest loginWithCodeRequest) throws Exception {
        return userService.loginWithCode(loginWithCodeRequest);
    }

}
