package org.zhangziqi.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zhangziqi.common.R;
import org.zhangziqi.utils.ErrorPage;

@FeignClient(name = "userService")
public interface UserServiceClient {
    @GetMapping("/find")
    @Retry(name = "retryApi", fallbackMethod = "findFall")
    @CircuitBreaker(name = "backendA", fallbackMethod = "findFall")
    public R<Page> find(@RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("/getAuth")
    public R<Boolean> getAuth(@RequestParam Integer userId);

    @GetMapping("/checkById")
    public R<Boolean> checkUserId(@RequestParam Integer userId);

    @GetMapping("/getEmail")
    public R<String> getEmail(@RequestParam Integer userId);

    default R<Page> findFall(@RequestParam Integer page, @RequestParam Integer size, Throwable throwable) {
        return R.ok(new ErrorPage("连接失败原因：" + throwable.getMessage()));
    }
}
