package com.feicent.mail.exception;

import org.springframework.mail.MailException;

@SuppressWarnings("serial")
public class MailSendException extends MailException {

	public MailSendException(String msg) {
        super(msg);
    }

    public MailSendException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
}
