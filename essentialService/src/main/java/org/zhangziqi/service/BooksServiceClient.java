package org.zhangziqi.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.zhangziqi.common.R;
import org.zhangziqi.dto.BooksDto;

import java.util.List;

@FeignClient(contextId = "bookClient", name = "bookManageService", path = "/books")
public interface BooksServiceClient {
    @GetMapping("/get/{id}")
    @CircuitBreaker(name = "backendA", fallbackMethod = "modifyFalse")
    public R<BooksDto> getBookById(@PathVariable("id") Integer id);

    @GetMapping
    @CircuitBreaker(name = "backendA", fallbackMethod = "findFall")
    public R<Page> getBooks(@RequestParam Integer page, @RequestParam Integer size);

    @CircuitBreaker(name = "backendA", fallbackMethod = "falseDelete")
    @DeleteMapping("/delete/{id}")
    public R<String> deleteById(@PathVariable("id") Integer id);

    @CircuitBreaker(name = "backendA", fallbackMethod = "findFall")
    @PostMapping("/add")
    public R<String> add(@RequestBody BooksDto books);

    @GetMapping("/find")
    @CircuitBreaker(name = "backendA", fallbackMethod = "findFall")
    public R<List<BooksDto>> query(@RequestParam String bookName);

    @PutMapping("/update")
    @CircuitBreaker(name = "backendA", fallbackMethod = "findFall")
    public R<String> update(@RequestBody BooksDto booksDto);
    default  R<Page> findFall(@RequestParam Integer page, @RequestParam Integer size,Throwable throwable){
        System.out.println(throwable.getMessage());
        return null;
    }
    default R<String> falseDelete(Integer id){
        return R.ok("book服务禁止级联删除所以异常（此方法测试熔断）");
    }
    default R<String> falseDelete(Throwable throwable){
        return R.ok("服务降级，原因是"+throwable.getMessage());
    }
    default R<String> modifyFalse(Throwable throwable) {
        return R.ok("你所访问的服务开小差了，原因+" + throwable.getMessage());
    }
}
