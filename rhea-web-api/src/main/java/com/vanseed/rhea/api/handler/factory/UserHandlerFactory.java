package com.vanseed.rhea.api.handler.factory;

import java.util.List;

import javax.annotation.PostConstruct;

import com.vanseed.rhea.api.handler.base.BaseHandlerFactory;
import com.vanseed.rhea.api.handler.user.IUserLoginHandler;
import com.vanseed.rhea.api.handler.user.IUserRegisterHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 
 * @author leon
 * @date 2019/05/19
 */
@Component
public class UserHandlerFactory extends BaseHandlerFactory {

	/*
	 * @Autowired private Handler[] handlerArr;
	 * @Autowired private List<Handler> handlerList;
	 * @Autowired private Map<String, Handler> handlerMap;
	 * Spring会查找应用上下文里所有实现Handler的bean放进handlerArr数组；
	 * 查找所有实现Handler的bean放进handlerList；
	 * 查找所有实现Handler的bean进handlerMap，key为bean的name。
 	 */
	@Autowired
	private List<IUserLoginHandler> userLoginHandlerList;
	@Autowired
	private List<IUserRegisterHandler> userRegisterHandlerList;

	/*
	 * map注入之后对map进行排序，并设置到HandlerMapping的map中
	 */
	@PostConstruct
	public void init(){
		putHandler(userLoginHandlerList);
		putHandler(userRegisterHandlerList);
	}



}
