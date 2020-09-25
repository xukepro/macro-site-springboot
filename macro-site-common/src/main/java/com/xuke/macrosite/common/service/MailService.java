package com.xuke.macrosite.common.service;

import javax.mail.MessagingException;

/**
 * Created by xuke on 2020/5/21
 */
public interface MailService {

    void sendMail(String email, String authCode) throws MessagingException;

}
