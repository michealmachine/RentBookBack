package org.zhangziqi.service;


import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.zhangziqi.model.User;
import org.zhangziqi.rep.UserRepository;


@SpringBootTest
public class UserServiceTest {



    @Resource
    private UserRepository userRepository;



    @Test
    @Transactional
    @Rollback(value = false)
    public void testCheckUser() {
        userRepository.save(new User(null,"admin","password","micheal0machine@gmail.com","13014141",true));

    }
}
