package com.vanseed.rhea.api.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.vanseed.rhea.common.utils.ResponseUtils;
import com.vanseed.saturn.core.mvc.IResponse;
import com.vanseed.saturn.support.exception.ServiceException;

@RestControllerAdvice
public class GlobalExceptionAdvice {
	@ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public IResponse serviceExceptionHandler(HttpServletRequest req, ServiceException se) throws Exception {
		IResponse response = ResponseUtils.getErrorRespose(se);
        return response;
    }
	
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public IResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		IResponse response = ResponseUtils.getErrorRespose();
        return response;
    }

}
