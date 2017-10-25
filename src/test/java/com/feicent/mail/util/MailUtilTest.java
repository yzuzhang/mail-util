package com.feicent.mail.util;

import com.feicent.mail.model.TemplateMailMessage;
import com.feicent.mail.model.TextMailMessage;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MailUtilTest {
	
    @Test
    public void testSend() throws Exception {
        TextMailMessage message = new TextMailMessage();
        message.setTo("80****92@qq.com");
        message.setSubject("测试邮件工具类");
        message.setText("这里是邮件发送工具类的正文内容");
        MailUtil.send(message);
    }

    @Test
    public void testSend1() throws Exception {
        TemplateMailMessage message = new TemplateMailMessage();
        message.setTo("80****92@qq.com");
        message.setSubject("测试邮件工具类");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", "测试用户");
        model.put("text","这里是通过模版显示的邮件内容 !");
        message.setModel(model);
        message.setTemplatePath("template/demo.vm");
        MailUtil.send(message);
    }
    
    @Test
    public void testSend2() throws Exception {
        TemplateMailMessage message = new TemplateMailMessage();
        message.setTo("80****92@qq.com");
        message.setSubject("测试邮件工具类");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("user", "zhang");
        model.put("text","这里是通过模版显示的邮件内容 !");
        message.setModel(model);
        message.setTemplatePath("template/demo.vm");
        MailUtil.send(message, "UTF-8");
    }
    
    public static void main(String[] args) {
		try {
			new MailUtilTest().testSend2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
