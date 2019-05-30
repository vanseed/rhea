/**
 * 
 */
package com.vanseed.rhea.common.utils;

import org.springframework.util.StringUtils;
import com.vanseed.rhea.common.mvc.MResponse;
import com.vanseed.saturn.support.exception.ServiceException;
import com.vanseed.saturn.support.util.LocaleUtil;
import com.vanseed.saturn.support.util.ThreadLocalUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * @author leon
 *
 */
public class ResponseUtils {

	public static final int SUCCESS = 200;
	public static final int ERROR = 500;
	public static final int BADREQUEST = 400;
	public static final int NOTLOGIN = 401;
	public static final int UNAUTHORIZED = 403;
	
	public static final String ERROR_MESSAGE = "操作失败！";
	public static final String ERROR_NOTLOGIN = "没有登录！";
	public static final String ERROR_BADREQUEST = "无效请求！";
	public static final String ERROR_UNAUTHORIZED = "没有权限！";
	
	public static final String DEFAULT_SUCCESS_MSG_CODE = "web.common.operate.success";
	public static final String DEFAULT_FAIL_MSG_CODE = "web.common.operate.fail";

	public static MResponse newSuccess(Map result) {
		MResponse response = new MResponse(SUCCESS,
				"success");
		response.setResult(result);
		return response;
	}

	public static Boolean isSuccess(MResponse response) {
		if(response.getStatus() == SUCCESS){
			return true;
		}else{
			return false;
		}
	}
	
	public static MResponse getErrorRespose() {
		return getErrorRespose("");
	}
	
	public static MResponse getErrorRespose(String code) {
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new MResponse(ERROR, MessageSourceUtils.getMessage(code, locale)==null?code:MessageSourceUtils.getMessage(code, locale));
		}else{
			return new MResponse(ERROR, ERROR_MESSAGE);
		}
	}
	
	public static MResponse getErrorRespose(String code, Object[] args) {
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new MResponse(ERROR, MessageSourceUtils.getMessage(code,args,locale));
		}else{
			return new MResponse(ERROR, ERROR_MESSAGE);
		}
	}

	public static MResponse getResponseBadRequest(String code){
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new MResponse(BADREQUEST, MessageSourceUtils.getMessage(code, locale)==null?code:MessageSourceUtils.getMessage(code, locale));
		}else{
			return new MResponse(BADREQUEST, ERROR_BADREQUEST);
		}
	}
	
	public static MResponse getResponseNotLogin(String code){
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new MResponse(NOTLOGIN, MessageSourceUtils.getMessage(code, locale)==null?code:MessageSourceUtils.getMessage(code, locale));
		}else{
			return new MResponse(NOTLOGIN, ERROR_NOTLOGIN);
		}
	}
	
	public static MResponse getResponseUnauthorized(String code){
		if(StringUtils.hasLength(code)){
			Locale locale = LocaleUtil.toLocale(ThreadLocalUtils.getLanguage());
			return new MResponse(UNAUTHORIZED, MessageSourceUtils.getMessage(code, locale)==null?code:MessageSourceUtils.getMessage(code, locale));
		}else{
			return new MResponse(UNAUTHORIZED, ERROR_UNAUTHORIZED);
		}
	}
	
	public static MResponse getErrorRespose(ServiceException exp) {
		if(StringUtils.hasLength(exp.getErrorCode())) {
			return getErrorRespose(exp.getErrorCode());
		} else {
			return new MResponse(ERROR, exp.getMessage());
		}
	}
	
	public static MResponse getSuccessRespose(Map<String, Object> obj) {
		String memo = "";
		try{
			memo = MessageSourceUtils.getMessage(DEFAULT_SUCCESS_MSG_CODE, LocaleUtil.toLocale(ThreadLocalUtils.getLanguage()));			
		}catch(Exception e){
			memo = DEFAULT_SUCCESS_MSG_CODE;
		}
		MResponse response = newSuccess(obj);
		response.setMemo(memo);
		return response;
	}

    public static MResponse getSuccessRespose(String key, Object obj) {
        String memo = "";
        try{
            memo = MessageSourceUtils.getMessage(DEFAULT_SUCCESS_MSG_CODE, LocaleUtil.toLocale(ThreadLocalUtils.getLanguage()));
        }catch(Exception e){
            memo = DEFAULT_SUCCESS_MSG_CODE;
        }
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        rtnMap.put(key, obj);
		MResponse response = newSuccess(rtnMap);
        response.setMemo(memo);
        return response;
    }
}
