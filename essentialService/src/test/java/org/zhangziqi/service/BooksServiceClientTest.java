package org.zhangziqi.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BooksServiceClientTest {
    @Resource
    BooksServiceClient booksServiceClient;
    @Test
    public void test(){
        System.out.println(booksServiceClient.getBookById(1));
    }
}
