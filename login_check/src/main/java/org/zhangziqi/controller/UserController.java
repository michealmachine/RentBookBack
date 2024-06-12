package org.zhangziqi.controller;

import jakarta.annotation.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhangziqi.common.R;
import org.zhangziqi.common.StatusCode;
import org.zhangziqi.dto.BooksDto;
import org.zhangziqi.dto.UserDto;
import org.zhangziqi.model.User;
import org.zhangziqi.rep.UserRepository;
import org.zhangziqi.service.UserService;

@RestController
public class UserController {
    @Resource
    UserService userService;
    @Resource
    UserRepository userRepository;

    @GetMapping("/find")
    public R<Page> find(@RequestParam Integer page, @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDto> pages = userService.getAllUsers(pageable);
        if (pages != null) return R.ok(pages);
        else return R.error(StatusCode.BAD_REQUEST, "查询失败");
    }

    @GetMapping("/getAuth")
    public R<Boolean> getAuth(@RequestParam Integer userId) {
        return R.ok(userRepository.findById(userId).map(it -> it.getIdAdmin()).orElse(false));
    }
    @GetMapping("/checkById")
    public R<Boolean> checkUserId(@RequestParam Integer userId){
        if(userService.getUserById(userId)!=null)return R.ok(true);
        else return R.ok(false);
    }
    @GetMapping("/getEmail")
    public R<String> getEmail(@RequestParam Integer userId){
        return R.ok(userRepository.findById(userId).map(User::getEmail).orElse("没有此用户"));
    }
}
