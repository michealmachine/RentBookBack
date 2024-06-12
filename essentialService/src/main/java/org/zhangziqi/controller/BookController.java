package org.zhangziqi.controller;

import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.zhangziqi.common.R;
import org.zhangziqi.dto.BooksDto;
import org.zhangziqi.service.BookService;
import org.zhangziqi.service.BooksServiceClient;

import java.util.List;

@RestController("/books")
public class BookController {
    @Resource
    BooksServiceClient booksService;
    @Resource
    BookService bookService;

    @GetMapping("/getById")
    public R<BooksDto> getById(@RequestParam Integer bookId){
        return booksService.getBookById(bookId);
    }

    @GetMapping("/findAll")
    public R<Page> findAll(@RequestParam Integer page, @RequestParam Integer size) {
        return booksService.getBooks(page, size);
    }

    @GetMapping("/findByName")
    public R<List<BooksDto>> findByName(@RequestParam String bookName) {
        return booksService.query(bookName);
    }

    @PutMapping("/updateBook")
    public R<String> updateBook(@RequestHeader("userId") Integer userId, @RequestBody BooksDto books) {
        return R.ok(bookService.update(userId, books));
    }

    @PostMapping("/addBook")
    public R<String> addBook(@RequestHeader("userId") Integer userId, @RequestBody BooksDto booksDto) {
        return R.ok(bookService.save(userId, booksDto));
    }

    @DeleteMapping("/delete")
    public R<String> delete(@RequestHeader("userId") Integer userId,@RequestParam Integer bookId){
        return R.ok(bookService.delete(userId,bookId));
    }
}
