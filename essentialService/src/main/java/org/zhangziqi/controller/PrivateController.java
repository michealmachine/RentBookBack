package org.zhangziqi.controller;

import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zhangziqi.common.R;
import org.zhangziqi.service.UserServiceClient;

@RestController
public class PrivateController {
    @Resource
    UserServiceClient userServiceClient;
    @GetMapping("/find")
    public R<Page> find(@RequestParam Integer page, @RequestParam Integer size){
        return userServiceClient.find(page,size);
    }

}
