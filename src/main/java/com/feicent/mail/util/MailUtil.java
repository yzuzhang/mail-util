package com.feicent.mail.util;

import com.feicent.mail.exception.MailSendException;
import com.feicent.mail.exception.TemplateMessagException;
import com.feicent.mail.factory.MailFactory;
import com.feicent.mail.model.TemplateMailMessage;
import com.feicent.mail.model.TextMailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * 邮件工具类
 */
public class MailUtil {

    private static MailFactory mailFactory;
    private static String defaultEncoding = "UTF-8";

    static {
    	mailFactory = new MailFactory(null);
    }
    
    /**
     * 发送简单邮件
     *
     * @param mailMessage
     * @throws MailSendException
     */
    public static void send(TextMailMessage mailMessage) throws MailSendException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFactory.getFrom());
        message.setTo(mailMessage.getTo());
        message.setSubject(mailMessage.getSubject());
        message.setText(mailMessage.getText());
        JavaMailSender mailSender = mailFactory.getMailSender();
        mailSender.send(message);
    }

    /**
     * 发送模版邮件
     *
     * @param mailMessage
     * @throws TemplateMessagException
     */
    public static void send(TemplateMailMessage mailMessage) throws TemplateMessagException {
        send(mailMessage, defaultEncoding);
    }

    /**
     * 发送模版邮件
     *
     * @param mailMessage
     * @throws TemplateMessagException
     */
    public static void send(TemplateMailMessage mailMessage, String encoding) throws TemplateMessagException {
        JavaMailSender mailSender = mailFactory.getMailSender();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, encoding);
        try {
            helper.setFrom(mailFactory.getFrom());
            helper.setTo(mailMessage.getTo());
            helper.setSubject(mailMessage.getSubject());
            String text = null;
            try {
                text = VelocityEngineUtils.mergeTemplateIntoString(mailFactory.getVelocityEngine(), mailMessage.getTemplatePath(), encoding, mailMessage.getModel());
            } catch (IOException e) {
                throw new TemplateMessagException(e.getMessage() + " getVelocityEngine error! ", e);
            }
            helper.setText(text, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new TemplateMessagException(e.getMessage() + " mail send error", e);
        }
    }
    
}
