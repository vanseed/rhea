package com.vanseed.rhea.service;

import com.vanseed.rhea.common.mvc.MSession;
import com.vanseed.saturn.support.exception.ServiceException;

/**
 * description
 *
 * @author leon
 * @date 2018/11/12
 */
public interface ISessionService {

	/**
	 * 通过sessionid获取userid
	 */
	public MSession getSession(String jsessonid);

	/**
	 * 通过userid获取sessionid
	 */
	public String getSessionIdByUser(String userId);

	/**
	 * 将当前用户设置到session中
	 */
	public void setSession(MSession session) throws ServiceException;

	/**
	 * 移除session
	 */
	public void removeSession(String sessionId);

	/**
	 * 移除session
	 */
	public void removeSessionByUserId(String userId);

}
