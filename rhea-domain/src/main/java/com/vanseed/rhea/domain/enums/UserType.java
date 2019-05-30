package com.vanseed.rhea.domain.enums;

import com.vanseed.saturn.core.IEnum;

public enum UserType implements IEnum {

	MOBILE(1,"手机"),
	ACCT(2,"账号"),
	WECHAT(3,"微信"),
	WEIBO(4,"微博");


	private int index;
	private String name;

	private UserType(int index, String name) {
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
	
	public static final UserType getStatusByIndex(int index){
		UserType status = null;
		for(UserType sign : UserType.values()){
			if(index == sign.getIndex()){
				status = sign;
			}
		}
		return status;
	}
}
