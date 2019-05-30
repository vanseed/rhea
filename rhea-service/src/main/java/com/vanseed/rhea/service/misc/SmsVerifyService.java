package com.vanseed.rhea.service.misc;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ebtrust.iwealth.common.exception.ErrorType;
import com.ebtrust.iwealth.common.exception.ServiceException;
import com.vanseed.rhea.domain.enums.SmsVerifyStatus;
import com.vanseed.rhea.domain.enums.SmsVerifyType;
import com.vanseed.rhea.domain.model.SmsVerify;
import com.vanseed.rhea.domain.model.User;
import com.vanseed.rhea.domain.repository.SmsVerifyRepository;
import com.vanseed.rhea.domain.repository.UserRepository;
import com.ebtrust.iwealth.util.SMSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("smsVerifyService")
public class SmsVerifyService implements ISmsVerifyService {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SmsVerifyRepository smsVerifyRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public SmsVerify addSmsVerify(SmsVerify smsVerify) {
		smsVerifyRepository.save(smsVerify);
		return smsVerify;
	}

	@Override
	public SmsVerify findSmsVerifyById(Long id) {
		return smsVerifyRepository.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void sendRegisterCode(Map<String, Object> params) {
		List<SmsVerify> svList = smsVerifyRepository.findByMobileAndVerifyStatusAndVerifyTypeOrderBySendTimeDesc(
				(String) params.get("mobile"), SmsVerifyStatus.NOVALIDATION.getIndex(), SmsVerifyType.REGISTER.getIndex());
		//TODO:加入次数限制
		SmsVerify sv = null;
		if (svList.size() > 0) {// 如果当前有未验证的短信验证码，置为已验证
			sv = svList.get(0);
//			Date now = new Date();
//			Long interval = (now.getTime() - sv.getSendTime().getTime())/1000;
//			if(interval<40){
//				throw new ServiceException("service.sms.sendOften", "");
//			}
//			sv.setChannel(1-sv.getChannel());
		}else{
			sv = new SmsVerify();
			Random r = new Random();
			sv.setCode(SMSUtils.getRandomCode());
			sv.setMobile((String) params.get("mobile"));
			User user = userRepository.findByMobile((String) params.get("mobile"));
			if (user!=null) {
				throw new ServiceException(ErrorType.init("service.mobile.existed"));
			} 
			sv.setVerifyType(SmsVerifyType.REGISTER.getIndex());
			sv.setVerifyStatus(SmsVerifyStatus.NOVALIDATION.getIndex());
			sv.setChannel(0);
		}
		sv.setSendTime(new Date());
		//非生产环境
		//if(!PropertiesUtil.getProp("profiles.activation").equalsIgnoreCase("product")){
		//	sv.setCode("123456");
		//}
		smsVerifyRepository.save(sv);
		String status = "200";
		try {
			//if(PropertiesUtil.getProp("profiles.activation").equalsIgnoreCase("product")){
			//	status = SMSUtils.sendVerifyMessage(sv.getMobile(), SMSUtils.VERIFY_TEMPLATE.replace("[*]", sv.getCode()));
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		if(!"200".equals(status)){
//			throw new ServiceException(status);
//		}
	}

	
	@Override
	public void sendResetPasswordCode(Map<String, Object> params) {
		//验证是否注册
		User user = userRepository.findByMobile((String) params.get("mobile"));
		if (user==null) {
			throw new ServiceException(ErrorType.init("service.mobile.notExisted"));
		} 
				
		//当天发送的验证短信条数不能超过6条
		List<SmsVerify> svList = smsVerifyRepository.findByMobieAndCurrentDate((String) params.get("mobile"));
				
		SmsVerify sv = null;
		if (svList.size() > 0) {// 如果当前有未验证的短信验证码，置为已验证
			if(svList.size()>=6){
				throw new ServiceException(ErrorType.init("service.sms.overLimit"));
			}
			List<SmsVerify> smsVerifyList = smsVerifyRepository.findByMobileAndVerifyStatusAndVerifyTypeOrderBySendTimeDesc((String) params.get("mobile"), SmsVerifyStatus.NOVALIDATION.getIndex(), SmsVerifyType.PASSWORD_RESET.getIndex());
			if(smsVerifyList!=null&&smsVerifyList.size()>0){
				sv = smsVerifyList.get(0);
//				sv.setChannel(1-sv.getChannel());
				sv.setVerifyType(SmsVerifyType.PASSWORD_RESET.getIndex());
			}else{
				sv = new SmsVerify();
				sv.setCode(SMSUtils.getRandomCode());
				sv.setMobile((String) params.get("mobile"));
				sv.setVerifyStatus(SmsVerifyStatus.NOVALIDATION.getIndex());
				sv.setUserId(null);
				sv.setVerifyType(SmsVerifyType.PASSWORD_RESET.getIndex());
			}
		}else{
			sv = new SmsVerify();
			sv.setCode(SMSUtils.getRandomCode());
			sv.setMobile((String) params.get("mobile"));
			sv.setVerifyStatus(SmsVerifyStatus.NOVALIDATION.getIndex());
			sv.setUserId(user.getId());
			sv.setVerifyType(SmsVerifyType.PASSWORD_RESET.getIndex());
			sv.setChannel(0);
		}
		sv.setSendTime(new Date());
//		if(!PropertiesUtil.getProp("profiles.activation").equalsIgnoreCase("product")){
//			sv.setCode("123456");
//		}
		smsVerifyRepository.save(sv);
				
		//发送短信
		String status = "200";
		try {
//			if(PropertiesUtil.getProp("profiles.activation").equalsIgnoreCase("product")){
//				status = SMSUtils.sendVerifyMessage(sv.getMobile(),SMSUtils.VERIFY_TEMPLATE.replace("[*]",  sv.getCode()));
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		if(!"200".equals(status)){
//			throw new ServiceException("service.common.server.error","");
//		}
	}

	@Override
	@Transactional
	public void sendSms(String mobile) {
		sendRegisterOrLoginSms(mobile,true);
	}

	private void sendRegisterOrLoginSms(String mobile,boolean checkUser){
		if(checkUser){
			User user = userRepository.findByMobile(mobile);
			if(user!=null){
				throw new ServiceException(ErrorType.init("service.mobile.existed"));
			}
		}
		
		List<SmsVerify> svList = smsVerifyRepository.findByMobileAndVerifyStatusAndVerifyTypeOrderBySendTimeDesc(
				mobile, SmsVerifyStatus.NOVALIDATION.getIndex(),SmsVerifyType.REGISTER.getIndex());
		SmsVerify sv = null;
		if (svList.size() > 0) {// 如果当前有未验证的短信验证码，置为已验证
			sv = svList.get(0);
			//如果时间间隔少于50秒，直接退出。
			//为什么50秒？客户端是限制为60秒，考虑到服务期间有可能存在的时间差，50秒能最大限度保证客户端60秒倒计时结束后重新请求，不会出现频率过快的提示
			if(sv.getSendTime()!=null && ( System.currentTimeMillis() - sv.getSendTime().getTime())<50*1000 )
			{
				throw new ServiceException(ErrorType.init("service.sms.sendOften"));
			}
//			sv.setChannel(1-sv.getChannel());
		}else{
			sv = new SmsVerify();
			sv.setCode(SMSUtils.getRandomCode());
			sv.setMobile(mobile);
			sv.setUserId(null);
			sv.setVerifyType(SmsVerifyType.REGISTER.getIndex());
			sv.setVerifyStatus(SmsVerifyStatus.NOVALIDATION.getIndex());
			sv.setChannel(0);
		}
		sv.setSendTime(new Date());
		try {
			smsVerifyRepository.save(sv);
		} catch (DataAccessException e1) {
			throw new ServiceException(ErrorType.init("service.common.server.error"));
		}
		String status = "200";
		try {
//			if(PropertiesUtil.getProp("profiles.activation").equalsIgnoreCase("product")){
//				status = SMSUtils.sendVerifyMessage(sv.getMobile(), SMSUtils.VERIFY_TEMPLATE.replace("[*]", sv.getCode()));
//			}
		} catch (Exception e) {
			logger.error("sendSms error,mobile is "+sv.getMobile()+",verify template is "+SMSUtils.VERIFY_TEMPLATE.replace("[*]", sv.getCode())+" ,exception is "+e.toString());
		}
//		if(!"200".equals(status)){
//			logger.error("sendSms error,mobile is "+sv.getMobile()+",verify template is "+SMSUtils.VERIFY_TEMPLATE.replace("[*]", sv.getCode())+"  error message is "+status);
//			throw new ServiceException(status);
//		}
	}

	@Override
	@Transactional
	public void sendDynamicSms(String mobile) {
		sendRegisterOrLoginSms(mobile,false);
	}
}
