package org.zhangziqi.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.zhangziqi.dto.BooksDto;
import org.zhangziqi.model.Books;
import org.zhangziqi.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.zhangziqi.common.R;
import org.zhangziqi.common.StatusCode;

import java.util.Date;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {
    @Resource
    BookService bookService;

    @GetMapping("/get/{id}")
    public R<BooksDto> getBookById(@PathVariable("id") Integer id) {
        return R.ok(bookService.queryBooksById(id));
    }

    @GetMapping
    public R<Page> getBooks(@RequestParam Integer page, @RequestParam Integer size) {
        log.info("这个服务被调用啦，（验证负载均衡）{}", new Date());
        return R.ok(bookService.getPage(PageRequest.of(page, size)));
    }

    @DeleteMapping("/delete/{id}")
    public R<String> deleteById(@PathVariable("id") Integer id) {
        return R.ok(bookService.deleteBook(id));
    }

    @PostMapping("/add")
    public R<String> add(@RequestBody BooksDto books) {
        return R.ok(bookService.saveBook(books));
    }

    @GetMapping("/find")
    public R<List<BooksDto>> query(@RequestParam String bookName) {
        return R.ok(bookService.queryBooksByName(bookName));
    }

    @PutMapping("/update")
    public R<String> update(@RequestBody BooksDto booksDto) {
        return R.ok(bookService.updateBook(booksDto));
    }
}
