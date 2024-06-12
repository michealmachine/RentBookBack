package org.zhangziqi.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.zhangziqi.common.R;

import java.util.List;

@SpringBootTest
public class UserServiceClientTest {
    @Resource
    UserServiceClient userServiceClient;
    @Test
    public void test(){
        R<Page> pageR = userServiceClient.find(1, 3);
        Page data = pageR.getData();

// 获取当前页的所有数据
        List content = data.getContent();

// 打印每一条数据
        for (Object obj : content) {
            System.out.println(obj);
        }

// 打印总页数
        System.out.println("Total Pages: " + data.getTotalPages());

// 打印总记录数
        System.out.println("Total Elements: " + data.getTotalElements());

// 打印当前页数
        System.out.println("Number: " + data.getNumber());

// 打印当前页的记录数
        System.out.println("Number of Elements: " + data.getNumberOfElements());

// 打印每页的记录数
        System.out.println("Size: " + data.getSize());

// 判断是否有下一页
        System.out.println("Has Next Page: " + data.hasNext());

// 判断是否有上一页
        System.out.println("Has Previous Page: " + data.hasPrevious());

// 判断是否是第一页
        System.out.println("Is First Page: " + data.isFirst());

// 判断是否是最后一页
        System.out.println("Is Last Page: " + data.isLast());
    }
}
