package com.vanseed.rhea.common.mvc;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vanseed.saturn.core.mvc.ISession;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MSession implements ISession
{
	@JsonProperty("session_id")
	private String sessionId;
	
	@JsonProperty("user_id")
	private Long userId;
	
	@JsonProperty("creat_time")
	private Date creatTime;

	@JsonProperty("access_time")
	private Date accessTime;

	@Override
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	@Override
	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
