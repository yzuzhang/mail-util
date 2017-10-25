package com.feicent.mail.factory;

import com.feicent.mail.util.ConfigUtil;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MailFactory {
	//发件人邮箱
    private String from;
    private Properties properties;
    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;
    
    public MailFactory() throws IOException{
    	this(null);
    }
    
    public MailFactory(String propertiesPath) {
        if(propertiesPath == null || propertiesPath.length() ==0){
            propertiesPath =  "mail.properties";
        }
        
        properties = ConfigUtil.getPropertiesFile(propertiesPath);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getProperty("mail.host"));
        mailSender.setUsername(properties.getProperty("mail.username"));
        mailSender.setPassword(properties.getProperty("mail.password"));
        mailSender.setPort(Integer.valueOf(properties.getProperty("mail.port", "25")));
        
        this.mailSender = mailSender;
        this.from = properties.getProperty("mail.from");
    }

    public JavaMailSender getMailSender() {
        return this.mailSender;
    }

    public VelocityEngine getVelocityEngine() throws IOException {
        if (this.velocityEngine != null) {
            return this.velocityEngine;
        }
        synchronized (this) {
            VelocityEngineFactoryBean factoryBean = new VelocityEngineFactoryBean();
            Map<String, Object> velocityPropertiesMap = new HashMap<String, Object>();
            velocityPropertiesMap.put("resource.loader", "class");
            velocityPropertiesMap.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            factoryBean.setVelocityPropertiesMap(velocityPropertiesMap);
            this.velocityEngine = factoryBean.createVelocityEngine();
        }
        return this.velocityEngine;
    }

    public String getFrom() {
        return from;
    }
}
