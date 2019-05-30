package com.vanseed.rhea.common.mvc;


import com.vanseed.saturn.core.mvc.IRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * description
 *
 * @author leon
 * @date 2019/04/17
 */
public class MRequest implements IRequest<MRequestHeader> {

    private HttpServletRequest wrappedRequest;

    private MRequestHeader requestHeader;

    private Map<String, Object> requestBody;

    public MRequest( MRequestHeader header) {
        this.requestHeader = header;
    }

    public MRequest(HttpServletRequest request, MRequestHeader header) {
        this.wrappedRequest = request;
        this.requestHeader = header;
    }

    @Override
    public HttpServletRequest getWrappedRequest() {
        return wrappedRequest;
    }

    public void setWrappedRequest(HttpServletRequest wrappedRequest) {
        this.wrappedRequest = wrappedRequest;
    }

    @Override
    public MRequestHeader getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(MRequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    @Override
    public Map getRequestParams() {
        return getWrappedRequest().getParameterMap();
    }

//    @Override
//    public String getParameter(String name) {
//        return getWrappedRequest().getParameter(name);
//    }

    public Map<String, Object> getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(Map<String, Object> requestMap) {
        this.requestBody = requestMap;
    }
}
