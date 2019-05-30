/**
 * 
 */
package com.vanseed.rhea.common.utils;

import com.vanseed.rhea.common.mvc.MRequest;
import com.vanseed.rhea.common.mvc.MRequestHeader;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import com.vanseed.saturn.support.util.JacksonJsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author leon
 * 
 */
public class RequestUtils {
	public static final String HEADER_NAME = "iwealth";
	public static final String SESSION_NAME = "jsessionid";

	public static MRequest formatRequest(HttpServletRequest request, Map<String, Object> requestBody){
		MRequestHeader requestHeader = getReqHeader(request);
		MRequest mRequest = new MRequest(request, requestHeader);
		mRequest.setRequestBody(requestBody);
		return mRequest;
	}

	public static MRequestHeader getReqHeader(HttpServletRequest request){
		String header = request.getHeader(HEADER_NAME);
		//String sessionId = request.getHeader(SESSION_NAME);
		MRequestHeader requestHeader = convertReqHeader(header);
		//requestHeader.setSessionId(sessionId);
		return requestHeader;
	}
	
	public static MRequestHeader getReqHeader(HttpHeaders headers) {
		String jsonHeader = headers.get("HEADER_NAME").get(0);
		//String sessionId = headers.get("jsessionid")!=null?headers.get("jsessionid").get(0):null;

		MRequestHeader requestHeader = convertReqHeader(jsonHeader);
		//requestHeader.setSessionId(sessionId);
		return requestHeader;
	}
	
	private static MRequestHeader convertReqHeader(String jsonHeader) {
		MRequestHeader header = new MRequestHeader();
		if (StringUtils.hasText(jsonHeader)) {
			Map<String,String> headerMap = JacksonJsonUtil.jsonToMap(jsonHeader);

			if(!StringUtils.isEmpty(headerMap.get("client-type"))){
				header.setClientType(Integer.valueOf(headerMap.get("client-type")));
			}
			if(!StringUtils.isEmpty(headerMap.get("client-version"))){
				header.setClientVersion(headerMap.get("client-version"));
			}
			if(!StringUtils.isEmpty(headerMap.get("device-type"))){
				header.setDeviceType(Integer.valueOf(headerMap.get("device-type")));
			}
			if(!StringUtils.isEmpty(headerMap.get("device-id"))){
				header.setDeviceId(headerMap.get("device-id"));
			}
			if(!StringUtils.isEmpty(headerMap.get("protocol-code"))){
				header.setProtocolCode(headerMap.get("protocol-code"));
			}
			if(!StringUtils.isEmpty(headerMap.get("protocol-version"))){
				header.setProtocolVersion(headerMap.get("protocol-version"));
			}
			if(!StringUtils.isEmpty(headerMap.get("session-id"))){
				header.setSessionId(headerMap.get("session-id"));
			}
			if(!StringUtils.isEmpty(headerMap.get("language"))){
				header.setLanguage(headerMap.get("language"));
			}
		}else{
			return null;
		}
		return header;
	}
	
	public static Boolean validateReqHeader(MRequestHeader header){
		if(header.getClientType()==null){
			return false;
		}
		if(StringUtils.isEmpty(header.getProtocolCode())){
			return false;
		}

		return true;
	}
}
