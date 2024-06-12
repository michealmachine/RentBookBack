package org.zhangziqi.service;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zhangziqi.dto.BooksDto;
import org.zhangziqi.model.Books;
import org.zhangziqi.model.Rent;
import org.zhangziqi.rep.BooksRepository;
import org.zhangziqi.rep.RentRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RentService {
    @Resource
    RentRepository repository;
    @Resource
    BooksRepository booksRepository;
    @Resource
    ModelMapper modelMapper;

    @Transactional
    public String borrow(Integer userId, Integer bookId, Date dueDate) {
        if (dueDate.compareTo(new Date()) <= 0) {
            return "你是不是傻，哪有借到之前的";
        }
        return booksRepository.findById(bookId).map(book -> {
            if (book.getAccount() > 0) {
                if (repository.findByUserIdAndBook(userId, book) != null) {
                    return "你已经借过这本书了";
                }
                book.setAccount(book.getAccount() - 1);
                booksRepository.save(book);
                repository.save(new Rent(null, userId, null, dueDate, new Books(bookId)));
                return "请与以下日期之前返回书籍" + dueDate;
            } else {
                return "这本书已经被借光了";
            }
        }).orElse("你丫哪来的数据，根本没有这本书");
    }

    @Transactional
    public String returnBook(Integer userId, Integer bookId) {
        Rent rent = repository.findByUserIdAndBook(userId, new Books(bookId));
        if (rent == null) {
            return "你从来没借过这本书";
        } else if (new Date().after(rent.getDueDate())) {
            return "你丫超时了,联系管理员吧";
        } else {
            repository.deleteByUserIdAndBook(userId, new Books(bookId));
            booksRepository.increaseBookQuantity(bookId);
            return "还书成功，欢迎下次使用";
        }
    }


    public List<BooksDto> queryById(Integer userId) {
        return repository.findByUserId(userId).stream()
                .map(Rent::getBook)
                .map(book -> modelMapper.map(book, BooksDto.class))
                .collect(Collectors.toList());
    }
}

