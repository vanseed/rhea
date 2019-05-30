package com.vanseed.rhea.service;

import com.vanseed.rhea.common.mvc.MSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.vanseed.saturn.support.exception.ServiceException;
import com.vanseed.saturn.support.util.JacksonJsonUtil;


/**
 * @author leon
 * TODO:应该批量发送指令，降低网络开销
 */
@Service("sessionService")
public class SessionService implements ISessionService {
	@Autowired
	@Qualifier("sessionRedisTemplate")
	private StringRedisTemplate sessionTemplate;

	/**
     * 通过sessionid获取userid
     */
	public MSession getSession(String jsessonid){
		if(StringUtils.isEmpty(jsessonid)){
			return null;
		}
		return getSessionFromRedis(jsessonid);
	}
	/**
     * 通过sessionid获取userid
     */
	private MSession getSessionFromRedis(String jsessonid){
		if(StringUtils.isEmpty(jsessonid)){
			return null;
		}
		String o = sessionTemplate.opsForValue().get(jsessonid);
		if(!StringUtils.isEmpty(o)){
			MSession session = JacksonJsonUtil.jsonToBean(o, MSession.class);
			return session;
		}
		return null;
	}
	
	/**
     * 通过userid获取sessionid
     */
	public String getSessionIdByUser(String userId){
		return getSessionIdFromRedisByUser(userId);
	}
	/**
     * 通过userid从redis中读取sessionid
     */
	private String getSessionIdFromRedisByUser(String userId){
		return sessionTemplate.boundValueOps(userId).get();
	}
	
	
	/**
     * 将当前用户设置到session中
     */
	public void setSession(MSession session) throws ServiceException {
		setSessionToRedis(session);
	}	
	/**
     * 将当前用户设置到redis中，为了保证一各用户只能同时在一个终端登录，需要有踢出机制
     */
	private void setSessionToRedis(MSession session) throws ServiceException{
		String strSession = JacksonJsonUtil.beanToJson(session);
		if(strSession==null){
			throw new ServiceException("service.common.session.error");
		}
		sessionTemplate.opsForValue().set(session.getUserId()+"", session.getSessionId());
		sessionTemplate.opsForValue().set(session.getSessionId(), strSession);
	}
	
	/**
     * 移除session
     */
	public void removeSession(String sessionId){
		removeSessionFromRedis(sessionId);
	}
	/**
     * 将redis中的session移除
     */
	private void removeSessionFromRedis(String sessionId){
		MSession session = getSessionFromRedis(sessionId);
		if(session!=null && session.getUserId()!=null){
			sessionTemplate.delete(String.valueOf(session.getUserId()));
		}		
		sessionTemplate.delete(sessionId);
	}
	
	/**
     * 移除session
     */
	public void removeSessionByUserId(String userId){
		removeSessionFromRedisByUserId(userId);
	}
	/**
     * 将redis中的session移除
     */
	private void removeSessionFromRedisByUserId(String userId){
		String sessionId = sessionTemplate.opsForValue().get(userId);
		if(sessionId!=null){
			sessionTemplate.delete(sessionId);
		}		
		sessionTemplate.delete(userId);
	}
}
