package com.vanseed.rhea.api.web;


import com.vanseed.rhea.api.handler.factory.MiscHandlerFactory;
import com.vanseed.rhea.api.handler.factory.UserHandlerFactory;
import com.vanseed.rhea.common.mvc.MRequest;
import com.vanseed.rhea.common.mvc.MRequestHeader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.vanseed.saturn.core.mvc.IHandler;
import com.vanseed.saturn.support.util.JacksonJsonUtil;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTests {

	@Test
	public void contextLoads() {
	}
	
	//模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。    
	private MockMvc mockMvc;
	
	//注入WebApplicationContext   
	@Autowired
    private WebApplicationContext wac;

    @Autowired
    UserHandlerFactory userHandlerFactory;

    @Autowired
    MiscHandlerFactory miscHandlerFactory;
	
	//初始化工作    
	@Before 
    public void setup() {    
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testhandler() throws Exception {
        Map map = miscHandlerFactory.testGetHandlerMap();
        MRequestHeader lHeader = new MRequestHeader();
        lHeader.setProtocolCode("1002");
        MRequestHeader rHeader = new MRequestHeader();
        rHeader.setProtocolCode("1001");

        Object o = userHandlerFactory.getHandler(new MRequest(lHeader));
        IHandler lHandler = userHandlerFactory.getHandler(new MRequest(lHeader));
        IHandler rHandler = userHandlerFactory.getHandler(new MRequest(rHeader));
        System.out.println( "========"+lHandler.getVersion());
        System.out.println( "========"+rHandler.getVersion());
    }

	@Test
    public void testEcho() throws Exception {
        Map<String, String> requestMap = new HashMap<String, String>();
        requestMap.put("user_name","Leon");
        HttpHeaders header = new HttpHeaders();
        header.add("iwealth","{\"client-version\":\"1.0.1\",\"device-type\":\"1\",\"device-id\":\"test_device\",\"protocol-code\":\"0000\",\"language\":\"zh_CN\"}" );

          
        MvcResult result = mockMvc.perform(post("/mobile")
                .headers(header)
        		.contentType(MediaType.APPLICATION_JSON_UTF8) //请求媒体类型
        		.content(JacksonJsonUtil.mapToJson(requestMap)))  //发送请求数据
                .andExpect(status().isOk())// 预期返回httpstatus状态       
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果    
            
        System.out.println("================"+result.getResponse().getContentAsString());
    }

    @Test
    public void testSecurity() throws Exception {
        Map<String, String> requestMap = new HashMap<String, String>();
        requestMap.put("user_name","Leon");
        HttpHeaders header = new HttpHeaders();
        header.add("iwealth","{\"client-version\":\"1.0.1\",\"device-type\":\"1\",\"device-id\":\"test_device\",\"protocol-code\":\"1001\",\"language\":\"zh_CN\"}" );

        MvcResult result = mockMvc.perform(post("/mobile")
                .headers(header)
                .contentType(MediaType.APPLICATION_JSON_UTF8) //请求媒体类型
                .content(JacksonJsonUtil.mapToJson(requestMap)))  //发送请求数据
                .andExpect(status().isOk())// 预期返回httpstatus状态
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println("================"+result.getResponse().getContentAsString());
    }
	
	@Test    
    public void testModify() throws Exception {    
		Map<String, String> map = new HashMap<>();  
        map.put("sample_id", "6");  
        map.put("amount", "99.99"); 
        
        MvcResult result = mockMvc.perform(post("/sample/modifyAmount")
        		.contentType(MediaType.APPLICATION_JSON_UTF8) //请求媒体类型
        		.content(JacksonJsonUtil.mapToJson(map)))  //发送请求数据
                .andExpect(status().isOk())// 预期返回httpstatus状态    
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果    
            
        System.out.println(result.getResponse().getContentAsString());    
    } 

}
