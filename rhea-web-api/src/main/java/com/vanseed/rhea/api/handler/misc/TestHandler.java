package com.vanseed.rhea.api.handler.misc;

import com.vanseed.rhea.api.handler.base.BaseHandler;
import com.vanseed.rhea.common.mvc.MRequest;
import com.vanseed.rhea.common.mvc.MRequestHeader;
import com.vanseed.rhea.common.mvc.MResponse;
import com.vanseed.rhea.common.utils.ResponseUtils;
import com.vanseed.saturn.core.annotation.AHandler;
import com.vanseed.saturn.support.util.ParamUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @author leon
 *
 */
@Component
@AHandler(protocol="0000", version = "1.0.0")
public class TestHandler extends BaseHandler implements ITestHandler {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public MResponse doHandler( MRequest request ) {
		return echo(request.getRequestHeader(), request.getRequestBody());
	}


	private MResponse echo( MRequestHeader header, Map params ) {

		String sessionid = header.getSessionId();
		String userName = ParamUtils.convertString(params.get("user_name"));

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "hi, this is v1.0.0,"+(userName!=null?userName:"unname")+"!");
		return ResponseUtils.getSuccessRespose(map);
	}


}
