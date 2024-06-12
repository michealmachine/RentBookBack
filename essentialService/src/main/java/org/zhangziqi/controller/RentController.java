package org.zhangziqi.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.zhangziqi.common.R;
import org.zhangziqi.dto.BooksDto;
import org.zhangziqi.service.RentService;
import org.zhangziqi.service.RentServiceClient;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/borrow")
public class RentController {
    @Resource
    RentServiceClient rentServiceClient;
    @Resource
    RentService rentService;
    @PostMapping("/rent")
    public R<String> rent(@RequestHeader("userId") Integer userId, @RequestParam Integer bookId, @RequestBody Date date) {
        return R.ok(rentService.rent(userId, bookId, date));
    }
    @DeleteMapping("/return")
    public R<String> returnBook(@RequestHeader("userId") Integer userId, @RequestParam Integer bookId){
        return rentServiceClient.returnBook(userId,bookId);
    }
    @GetMapping("/find")
    public R<List<BooksDto>> find(@RequestHeader("userId") Integer userId){
        return rentServiceClient.find(userId);
    }
}
