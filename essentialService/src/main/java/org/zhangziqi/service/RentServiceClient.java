package org.zhangziqi.service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.zhangziqi.common.R;
import org.zhangziqi.dto.BooksDto;

import java.util.Date;
import java.util.List;

@FeignClient(contextId = "rentClient", name = "bookManageService", path = "/rent")
public interface RentServiceClient {
    @RateLimiter(name = "backendB", fallbackMethod = "fall")
    @PostMapping("/borrow")
    public R<String> borrow(@RequestParam Integer userId, @RequestParam Integer bookId, @RequestBody Date date);

    @DeleteMapping("/return")
    public R<String> returnBook(@RequestParam Integer userId, @RequestParam Integer bookId);

    @GetMapping("/find/{userId}")
    public R<List<BooksDto>> find(@PathVariable Integer userId);

    default R<String> fall(Integer userId, Integer bookId, Date date, Throwable throwable) {
        return R.ok("你消停点,一次性借那么多书不累吗(限流操作)");
    }
}
