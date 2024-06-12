package org.zhangziqi.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zhangziqi.common.R;
import org.zhangziqi.dto.BooksDto;

@Service
@Slf4j
public class BookService {
    @Resource
    BooksServiceClient booksServiceClient;
    @Resource
    UserServiceClient userServiceClient;

    public String update(Integer userId, BooksDto books) {
        if (userServiceClient.getAuth(userId).getData()) {
            Integer id = books.getId();
            if (id == null) {
                return "更新失败，书籍的ID不能为空";
            }
            R<BooksDto> bookResponse = booksServiceClient.getBookById(id);
            if (bookResponse.getData() != null) {
                booksServiceClient.update(books);
                return "更新成功";
            } else {
                return "更新失败，没有找到ID为 " + id + " 的书籍";
            }
        }
        return "哥们你没权限，还想搞事情";
    }

    public String save(Integer userId, BooksDto books) {
        if (userServiceClient.getAuth(userId).getData()) {
            if (books.getAccount() == null) {
                return "更新失败，书籍的数量不能为空";
            }
            return booksServiceClient.add(books).getData();
        }
        return "哥们你没权限，还想搞事情";
    }

    public String delete(Integer userId, Integer bookId) {
        if (userServiceClient.getAuth(userId).getData()) {
            R<String> deleteResponse = booksServiceClient.deleteById(bookId);
            if (deleteResponse.getData() != null) {
                return deleteResponse.getData();
            }
        }
        return "哥们你没权限，还想搞事情";
    }
}
