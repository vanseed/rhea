package com.vanseed.rhea.domain.enums;

import com.vanseed.saturn.core.IEnum;

/**
 * 短信类型枚举
 * @author leon
 *
 */
public enum SmsVerifyType implements IEnum {
	REGISTER(1,"注册"),
	LOGIN(2,"登录"),
	PASSWORD_RESET(3,"找回密码");
	
	private int index;
	private String name;
	
	private SmsVerifyType(int index, String name) {
		this.index = index;
		this.name = name;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public String getName() {
		return name;
	}
	
	public static final SmsVerifyType getStatusByIndex(int index){
		SmsVerifyType type = null;
		for(SmsVerifyType at :SmsVerifyType.values()){
			if(index == at.getIndex()){
				type = at;
			}
		}
		return type;
	}
}
