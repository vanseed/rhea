package com.vanseed.rhea.api.security;

import com.vanseed.rhea.common.mvc.MRequestHeader;
import com.vanseed.rhea.common.mvc.MResponse;
import com.vanseed.rhea.common.mvc.MSession;
import com.vanseed.rhea.common.utils.RequestUtils;
import com.vanseed.rhea.common.utils.ResponseUtils;
import com.vanseed.rhea.service.ISessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import com.vanseed.saturn.support.util.JacksonJsonUtil;
import com.vanseed.saturn.support.util.LocaleUtil;
import com.vanseed.saturn.support.util.ThreadLocalUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * description
 *
 * @author leon
 * @date 2019/04/18
 */
public class SecurityInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected ISessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();

        //获取否methodHandler
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 拦截移动端请求
        if (uri.startsWith("/mobile")) {
            MRequestHeader mHeader = RequestUtils.getReqHeader(request);
            MSession mSession = null;

            //请求格式错误
            if(mHeader==null){
                buildResponse(response, ResponseUtils.getResponseBadRequest("web.common.client.badrequest"));
                return false;
            }

            //设置线程变量
            if(StringUtils.hasText(mHeader.getSessionId())){
                mSession = sessionService.getSession(mHeader.getSessionId());
            }
            if(mSession!=null){
                ThreadLocalUtils.setUserId( mSession.getUserId());
            }else{//未登录
                ThreadLocalUtils.setUserId( null );
            }
            if(mHeader.getLanguage()!=null){
                ThreadLocalUtils.setLanguage(mHeader.getLanguage());
            }else{//默认语言
                ThreadLocalUtils.setLanguage(LocaleUtil.DEFAULT_LOCALE.toString() );
            }

            //需要登录的API
            if(!isNotPermission(mHeader) &&  (mSession ==null || mSession.getUserId()==null || mSession.getUserId()<0 )){
                buildResponse(response, ResponseUtils.getResponseNotLogin("web.user.notLogin"));
                return false;
            }
        }else {//非移动端请求（比如其他的对外接口等）
            //TODO:需要添加相关的安全限制，目前全部禁用
            buildResponse(response, ResponseUtils.getResponseUnauthorized("web.common.permission.error"));
            return false;
        }

        return true;
    }


    private boolean isNotPermission(MRequestHeader mHeader) {
        List<String> pList = Arrays.asList("00**","0101","0102","0103","0104");
        String protocolId = mHeader.getProtocolCode();
        AntPathMatcher matcher = new AntPathMatcher();
        for (String p : pList) {
            if (matcher.match(p,protocolId)) {
                return true;
            }
        }
        return false;
    }

    private void buildResponse(HttpServletResponse response, MResponse mResponse) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            String json = JacksonJsonUtil.beanToJson(mResponse);
            logger.info("拦截器返回：-------" + json);
            writer.print(json);
        } catch (IOException e) {
            logger.error("response error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
