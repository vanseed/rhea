
/**
 * 
 */
package com.ebtrust.iwealth.oss.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author leon
 * 
 */
@Controller
@RequestMapping("/")
public class RootController extends BaseController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());  
	
	/**
	 * 登录页
	 */
	@RequestMapping(value = "/default")
	public String loginPage(
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		model.addAttribute("host", "comb acct");
		return "login";
	}
	
	/**
	 * 登录
	 * shiro会拦截配置中"loginUrl"指向的url的post请求，处理登录功能，如果登录成功会跳转到"successUrl"指向的url
	 * 在controller中的登录过程其实只是处理异常的相关信息，具体的登录验证交给shiro来处理 
	 */
	@RequestMapping(value = "/login")
	public String login(
			Map<String, Object> map,
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		logger.debug("login");
		
		String exception = (String) request.getAttribute("shiroLoginFailure");
		logger.info("exception=" + exception);
		String msg = "";
		if (exception != null) {
			if ("kaptchaValidateFailed".equals(exception)) {
				logger.info("kaptchaValidateFailed -- > 验证码错误");
				msg = "kaptchaValidateFailed -- > 验证码错误";
			} else {
				msg = "else >> "+exception;
				System.out.println("else -- >" + exception);
			}
		}
		map.put("msg", msg);
		// 此方法不处理登录成功,由shiro进行处理
		return "index";
	}
	
	/**
	 * 退出
	 */
	@RequestMapping(value = "/logout.do")
	public String logoutPage(
			HttpServletRequest request, HttpServletResponse response,
			Model model){	
		return "admin/login";
	}
	
	/**
	 * 首页
	 */
	@RequestMapping(value = "/index")
	public String indexPage(
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		
		return "index";
	}
	
	/**
	 * 403
	 */
	@RequestMapping(value = "/403")
	public String errorPage403(
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		return "errorpage/403";
	}
	
	/**
	 * 404
	 */
	@RequestMapping(value = "/404")
	public String errorPage404(
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		return "errorpage/404";
	}
	
	/**
	 * 505
	 */
	@RequestMapping(value = "/500")
	public String errorPage500(
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		return "errorpage/500";
	}
	
	/**
	 * 测试
	 */
	@RequestMapping(value = "/testPage")
	public String testPage(
			HttpServletRequest request, HttpServletResponse response,
			Model model){
		
		return "admin/testPage";
	}
		
}