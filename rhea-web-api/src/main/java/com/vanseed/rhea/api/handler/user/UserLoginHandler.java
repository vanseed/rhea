package com.vanseed.rhea.api.handler.user;

import java.util.HashMap;
import java.util.Map;

import com.vanseed.rhea.api.handler.base.BaseHandler;
import com.vanseed.rhea.common.mvc.MRequest;
import com.vanseed.rhea.common.mvc.MRequestHeader;
import com.vanseed.rhea.common.mvc.MResponse;
import com.vanseed.rhea.common.utils.ResponseUtils;
import com.vanseed.rhea.domain.model.User;
import com.vanseed.rhea.service.user.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.vanseed.saturn.core.annotation.AHandler;
import com.vanseed.saturn.support.exception.ServiceException;
import com.vanseed.saturn.support.util.ParamUtils;


/**
 * 
 * @author leon
 *
 */
@Component
@AHandler(protocol="1002", version = "1.0.0")
public class UserLoginHandler extends BaseHandler implements IUserLoginHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserService userService;

	@Override
	public MResponse doHandler( MRequest request ) {
		return login(request.getRequestHeader(), request.getRequestBody());
	}


	private MResponse login( MRequestHeader header, Map params ) {
		String userName = ParamUtils.convertString(params.get("user_name"));
		String sessionid = header.getSessionId();
		
		User user = null;

		try 
		{
			//params.put("client_ip", header.getcIp());
			params.put("device_type", header.getDeviceType());
			params.put("device_id", header.getDeviceId());
	
			user = userService.login(sessionid,  params) ;
					
		} catch (ServiceException e) {
			return ResponseUtils.getErrorRespose(e);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("jsessionid", sessionid);
		return ResponseUtils.getSuccessRespose(map);
	}


}
