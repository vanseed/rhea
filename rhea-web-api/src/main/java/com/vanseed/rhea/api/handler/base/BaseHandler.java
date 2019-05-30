package com.vanseed.rhea.api.handler.base;

import com.vanseed.rhea.service.ISessionService;
import com.vanseed.saturn.core.annotation.AHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class BaseHandler {
	@Autowired
	protected ISessionService sessionService;

	public String getProtocol() {
		AHandler annotation = this.getClass().getAnnotation(AHandler.class);
		return annotation!=null?annotation.protocol():null;
	}

	public String getVersion() {
		AHandler annotation = this.getClass().getAnnotation(AHandler.class);
		return annotation!=null?annotation.version():null;
	}

}
