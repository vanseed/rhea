package com.vanseed.rhea.service.user;

import com.vanseed.rhea.common.mvc.MSession;
import com.vanseed.rhea.domain.enums.UserStatus;
import com.vanseed.rhea.domain.enums.UserType;
import com.vanseed.rhea.domain.model.User;
import com.vanseed.rhea.domain.repository.UserRepository;
import com.vanseed.rhea.service.BaseService;
import com.vanseed.saturn.support.exception.ErrorType;
import com.vanseed.saturn.support.exception.ServiceException;
import com.vanseed.saturn.support.util.EncryptUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * description
 *
 * @author leon
 * @date 2019/05/12
 */
@Service("userService")
public class UserService extends BaseService implements IUserService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@Override
	public User newUser(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public User findUserById(Long id) {
		User user = userRepository.getOne(id);
		//userRepository.findById(id).orElse(null);
		return user;
	}
	
	@Override
	public User findUserByName(String userName) {
		User user = userRepository.findByUserName(userName);
		return user;
	}

	@Transactional
	@Override
	public User register(String sessionId, Map<String, Object> paraMap) {
		String mobile = paraMap.get("mobile").toString();
		String password = EncryptUtils.MD5(paraMap.get("password").toString());
		String verifyCode = paraMap.get("verify_code").toString();
		String invitationUserCode = paraMap.get("invit_code").toString();
		Integer deviceType = (Integer)paraMap.get("device_type");
		String deviceId = (String)paraMap.get("device_id");
		Date now = new Date();

		User us = userRepository.findByMobile(mobile);
		if (us != null) {
			throw new ServiceException( ErrorType.init("service.mobile.existed") );
		}

		//校验短信验证码
//		List<SmsVerify> svList = smsVerifyRepository
//				.findByMobileAndVerifyStatusAndVerifyTypeOrderBySendTimeDesc(
//						mobile, SmsVerifyStatus.NOVALIDATION.getIndex(),
//						SmsVerifyType.REGISTER.getIndex());
//		if (svList == null || svList.isEmpty()) {
//			throw new ServiceException("service.sms.verifyCodeNotFound", "");
//		} else {
//			SmsVerify smsVerify = svList.get(0);
//			if (!smsVerify.getCode().equals(verifyCode)) {
//				throw new ServiceException("service.sms.verifyFailed", "");
//			} else {
//				smsVerify .setVerifyStatus(SmsVerifyStatus.VALIDATION.getIndex());
//				smsVerify.setVerifyTime(new Date());
//				smsVerifyRepository.save(smsVerify);
//			}
//		}

		//新增用户
		User user = new User();
		user.setUserName(mobile);
		user.setPassword(password);
		user.setMobile(mobile);
		user.setInvitationUserCode(invitationUserCode);
		user.setUserType(UserType.MOBILE.getIndex());
		user.setRegDate(now);
		user.setCreateTime(now);
		user.setUpdateTime(now);
		user.setUserStatus(UserStatus.NORMAL.getIndex());
		userRepository.save(user);

//		user.setUserCode(RadixUtils.randomCode(user.getId()));
//		userRepository.save(user);

		//处理session
		sessionService.removeSession(sessionId);
		MSession session = new MSession();
		session.setSessionId(sessionId);
		session.setUserId(user.getId());
		session.setCreatTime(now);
		sessionService.setSession(session);

		//新增用户的异步事件
//		UserCreateEvent event = new UserCreateEvent();
//		event.setUserId(user.getId());
//		EventBusFactory.getEventBus().post(event);

		return user;
	}

	@Transactional
	@Override
	public User login(String sessionId, Map<String, Object> paraMap)
	{
		Date now = new Date();
		Integer deviceType = (Integer)paraMap.get("device_type");
		String deviceId = (String)paraMap.get("device_id");
		String username = (String)paraMap.get("user_name");
		String password = (String)paraMap.get("password");

		User user = userRepository.findByUserName(username);

		if(user==null){
			throw new ServiceException(ErrorType.init("service.mobile.notRegistered",""));
		}else if(!user.getPassword().equals(EncryptUtils.MD5(password))){
			throw new ServiceException(ErrorType.init("service.user.passwordError",""));
		}else if (user.getUserStatus() == UserStatus.DISABLE.getIndex()) {
			throw new ServiceException(ErrorType.init("service.user.disabled", ""));
		}else{//正常登录
			//处理session
			sessionService.removeSessionByUserId( String.valueOf(user.getId()) );
			MSession newSession = new MSession();
			newSession.setSessionId(sessionId);
			newSession.setUserId(user.getId());
			newSession.setCreatTime(now);
			sessionService.setSession(newSession);

		}

//		LoginEvent loginEvent = new LoginEvent();
//		loginEvent.setUserId(user.getId());
//		loginEvent.setDeviceId(deviceId);
//		EventBusFactory.getEventBus().post(loginEvent);

		return user;
	}
}
