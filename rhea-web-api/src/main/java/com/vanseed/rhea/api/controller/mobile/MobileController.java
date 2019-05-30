package com.vanseed.rhea.api.controller.mobile;

import com.vanseed.rhea.api.handler.base.BaseHandlerFactory;
import com.vanseed.rhea.common.enums.ClientType;
import com.vanseed.rhea.common.mvc.MRequest;
import com.vanseed.rhea.common.mvc.MResponse;
import com.vanseed.rhea.common.utils.RequestUtils;
import com.vanseed.rhea.common.utils.ResponseUtils;
import com.vanseed.saturn.core.mvc.IHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @author leon
 * 
 */
@Controller
public class MobileController {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("baseHandlerFactory")
	private BaseHandlerFactory handlerFactory;


	private AntPathMatcher matcher = new AntPathMatcher();

	@RequestMapping(value = "mobile")
	@ResponseBody
	public MResponse mobile(@RequestBody(required=false) Map<String, Object> requestBody,
							HttpServletRequest request, HttpServletResponse response ) throws Exception {

		MRequest mRequest = RequestUtils.formatRequest(request, requestBody);
		mRequest.getRequestHeader().setClientType(ClientType.APP.getIndex());

		logger.debug("protocol:" + mRequest.getRequestHeader().getProtocolCode());

		IHandler<MRequest, MResponse> handler = handlerFactory.getHandler(mRequest);

		if(handler == null){
			return ResponseUtils.getResponseBadRequest("");
		}

		return handler.doHandler(mRequest);
	}
}
