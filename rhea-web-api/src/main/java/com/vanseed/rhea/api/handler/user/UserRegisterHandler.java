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
import org.springframework.util.StringUtils;
import com.vanseed.saturn.core.annotation.AHandler;
import com.vanseed.saturn.support.exception.ServiceException;


@Component
@AHandler(protocol="1001", version = "1.0.0")
public class UserRegisterHandler extends BaseHandler implements IUserRegisterHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserService userService;
	
	@Override
	public MResponse doHandler( MRequest request ) {
		return register(request.getRequestHeader(), request.getRequestBody());
	}

	private MResponse register( MRequestHeader header, Map<String, Object> params)
	{
		String sessionId = header.getSessionId();
		
		params.put("device_type", header.getDeviceType());
		params.put("device_id", header.getDeviceId());
		params.put("client_type", header.getClientType());

		Integer clientType = header.getClientType();
		
		
		User user = null;
			
		try {
			if(clientType>100){ //外部注册不需要校验短信验证码
				//user = userService.registerExt(mobile, password, verifyCode, header.getDeviceId(), header.getUserAgent(), header.getcIp(),invitationCode,header.getDeviceType(), clientType);
			}
			else{
				user = userService.register(sessionId, params); 
			}
				
		} catch (ServiceException e) {
			return ResponseUtils.getErrorRespose(e);
		}
		
		if(user==null){
			return ResponseUtils.getErrorRespose("web.common.server.error");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		if(user!=null){
			map.put("user", user);
			if(!StringUtils.isEmpty(sessionId)){
				map.put("jsessionid", sessionId);
			}
		}
		return ResponseUtils.getSuccessRespose(map);
	}

}
