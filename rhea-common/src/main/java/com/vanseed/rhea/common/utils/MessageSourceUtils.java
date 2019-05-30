/**
 * 
 */
package com.vanseed.rhea.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.vanseed.saturn.support.exception.ServiceException;

import java.util.Locale;

/**
 * @author leon
 *
 */
@Component
public class MessageSourceUtils {

	private static MessageSource messageSource;
	
	@Autowired(required = true)
    public void setMessageSource(MessageSource messageSource) {
		MessageSourceUtils.messageSource = messageSource;  
    }
		
	public static String getMessage(String code) {
		return getMessage(code, new Object[0], Locale.US);
	}
	
	public static String getMessage(String code, Locale locale) {
		return getMessage(code, new Object[0], locale);
	}
	
	public static String getMessage(String code, Object[] args) {
		return getMessage(code, args, Locale.US);
	}
	
	public static String getMessage(String code, Object[] args, Locale locale) {
		String msg = null;
		try {
			msg = messageSource.getMessage(code, args, locale);
		}catch(NoSuchMessageException e) {
			msg = null;
		}
		return msg;
	}
	
	public static String getMessage(ServiceException exp) {
			return getMessage(exp, Locale.US);
	}
	
	public static String getMessage(ServiceException exp, Locale locale) {
		if(!StringUtils.isEmpty(exp.getErrorCode())) {
			return getMessage(exp.getErrorCode(), locale);
		} else {
			return exp.getMessage();
		}
	}
	
}
