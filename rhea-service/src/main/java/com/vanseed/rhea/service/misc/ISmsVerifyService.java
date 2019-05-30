package com.vanseed.rhea.service.misc;

import com.vanseed.rhea.domain.model.SmsVerify;

import java.util.Map;

/**
 * description
 *
 * @author leon
 * @date 2018/11/12
 */
public interface ISmsVerifyService {

	public SmsVerify addSmsVerify(SmsVerify smsVerify);

	public SmsVerify findSmsVerifyById(Long id);

	public void sendRegisterCode(Map<String, Object> params);

	public void sendResetPasswordCode(Map<String, Object> params);

	public void sendSms(String mobile);

	public void sendDynamicSms(String mobile);
}
