package com.vanseed.rhea.domain.enums;

import com.vanseed.saturn.core.IEnum;

public enum SmsVerifyStatus implements IEnum {

	NOVALIDATION(0,"未验证"),
	VALIDATION(1,"已验证"),
	FAIL(-1,"已失效");
	
	private int index;
	private String name;
	
	private SmsVerifyStatus(int index, String name) {
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
	public static final SmsVerifyStatus getStatusByIndex(int index){
		SmsVerifyStatus status = null;
		for(SmsVerifyStatus verifyStatus :SmsVerifyStatus.values()){
			if(index == verifyStatus.getIndex()){
				status = verifyStatus;
			}
		}
		return status;
	}
	
}
