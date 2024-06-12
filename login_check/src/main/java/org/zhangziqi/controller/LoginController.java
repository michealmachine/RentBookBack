package org.zhangziqi.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zhangziqi.common.R;
import org.zhangziqi.common.StatusCode;
import org.zhangziqi.model.User;
import org.zhangziqi.service.UserService;
import org.zhangziqi.utils.JwtUtil;

@Slf4j
@RestController
public class LoginController {
    @Resource
    UserService userService;
    @PostMapping("/loginCheck")
    public R<String> check(@RequestBody User user){
        log.info(user.getUsername());
        if(userService.checkUser(user))return R.ok(JwtUtil.generateToken(user.getUsername(),userService.getId(user.getUsername())));
        else return R.ok("密码错误");
    }


}
