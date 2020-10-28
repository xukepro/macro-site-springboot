package com.xuke.macrosite.service.impl;

import com.xuke.macrosite.common.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by xuke on 2020/5/21
 */
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSenderImpl mailSender;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public void sendMail(String email, String authCode) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("注册验证码");
        helper.setText("验证码是" + authCode + "," + AUTH_CODE_EXPIRE_SECONDS + "秒内有效");
        helper.setTo(email);
        helper.setFrom("1020194752@qq.com");

        mailSender.send(mimeMessage);
    }
}
