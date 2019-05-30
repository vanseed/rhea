package com.vanseed.rhea.domain.enums;

import com.vanseed.saturn.core.IEnum;

public enum UserStatus implements IEnum {

	DISABLE(0,"禁用"),
	NORMAL(1,"正常");

	private int index;
	private String name;

	private UserStatus(int index, String name) {
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
	
	public static final UserStatus getStatusByIndex(int index){
		UserStatus status = null;
		for(UserStatus sign : UserStatus.values()){
			if(index == sign.getIndex()){
				status = sign;
			}
		}
		return status;
	}
}
