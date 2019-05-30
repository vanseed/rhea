package com.vanseed.rhea.api.handler.factory;

import com.vanseed.rhea.api.handler.base.BaseHandlerFactory;
import com.vanseed.rhea.api.handler.misc.ITestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author leon
 * @date 2019/05/19
 */
@Component
public class MiscHandlerFactory extends BaseHandlerFactory {

	@Autowired
	private List<ITestHandler> testHandlerList;

	/*
	 * map注入之后对map进行排序
	 */
	@PostConstruct
	public void init(){
		putHandler(testHandlerList);
	}

	//测试
	public Map  testGetHandlerMap(){
		return getHandlerMap();
	}

}
