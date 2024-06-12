package org.zhangziqi.service;

import jakarta.annotation.Resource;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import org.zhangziqi.dto.EmailDetails;

import java.util.Date;
import java.util.Map;

@Service
public class RentService {
    @Resource
    RentServiceClient rentServiceClient;
    @Resource
    UserServiceClient userServiceClient;
    @Resource
    BooksServiceClient booksServiceClient;
    @Resource
    StreamBridge bridge;

    public String rent(Integer userId, Integer bookId, Date date) {
        if (!userServiceClient.checkUserId(userId).getData()) {
            return "根本没有这个用户Id，你是怎么访问我的?";
        }
        if (date.before(new Date())) return "傻冒，你应该借从今往后的书";
        String data = rentServiceClient.borrow(userId, bookId, date).getData();

        if(data.contains("请与以下日期之前返回书籍")){
            String email = userServiceClient.getEmail(userId).getData();
            String bookName = booksServiceClient.getBookById(bookId).getData().getBookName();
            bridge.send("send-email",new EmailDetails(date,email,bookName));
            data=data+"请查看邮箱，借书信息已通知您";
        }
        return data;
    }
}
