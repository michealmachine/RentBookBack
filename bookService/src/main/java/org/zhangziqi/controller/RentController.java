package org.zhangziqi.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.zhangziqi.common.R;
import org.zhangziqi.dto.BooksDto;
import org.zhangziqi.model.Books;
import org.zhangziqi.model.Rent;
import org.zhangziqi.rep.RentRepository;
import org.zhangziqi.service.RentService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rent")
public class RentController {
    @Resource
    RentService rentService;

    @PostMapping("/borrow")
    public R<String> borrow(@RequestParam Integer userId, @RequestParam Integer bookId, @RequestBody Date date) {
        return R.ok(rentService.borrow(userId, bookId, date));
    }

    @DeleteMapping("/return")
    public R<String> returnBook(@RequestParam Integer userId, @RequestParam Integer bookId) {
        return R.ok(rentService.returnBook(userId, bookId));
    }
    @GetMapping("/find/{userId}")
    public R<List<BooksDto>> find(@PathVariable Integer userId){
        return R.ok(rentService.queryById(userId));
    }
}
