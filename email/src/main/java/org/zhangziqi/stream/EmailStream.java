package org.zhangziqi.stream;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.zhangziqi.dto.EmailDetails;
import org.zhangziqi.service.EmailSendService;

import java.util.function.Consumer;

@Component
public class EmailStream {
    @Resource
    EmailSendService emailSendService;
    @Bean
    public Consumer<EmailDetails> consumer(){
        return emailDetails->{
            System.out.println(emailDetails);
            emailSendService.sendEmail(emailDetails);
        };
    }
}
