package org.zhangziqi.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.zhangziqi.dao.EmailMapper;
import org.zhangziqi.dto.EmailDetails;

@Service
public class EmailSendService {
    @Resource
    EmailMapper emailMapper;
    @Resource
    JavaMailSender sender;
    public void sendEmail(EmailDetails emailDetails){
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件标题
        message.setSubject("你有一本新的书，别忘记还");
        //设置邮件内容
        message.setText("嘿，你借的"+emailDetails.getBookName()+"书，要在如下时间之前还，不然失效"
                +emailDetails.getDate());
        //设置邮件发送给谁，可以多个，这里就发给你的QQ邮箱
        message.setTo(emailDetails.getEmail());
        //邮件发送者，这里要与配置文件中的保持一致
        message.setFrom("madpsycho@163.com");
        emailMapper.insert(emailDetails);
        sender.send(message);
    }
}
