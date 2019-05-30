package com.vanseed.rhea.domain.enums;

import com.vanseed.saturn.core.IEnum;

public enum IDStatus implements IEnum {

	UNCERTIFIED(0,"未认证"),
	CERTIFIED(1,"已认证");


	private int index;
	private String name;

	private IDStatus(int index, String name) {
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
	
	public static final IDStatus getStatusByIndex(int index){
		IDStatus status = null;
		for(IDStatus sign : IDStatus.values()){
			if(index == sign.getIndex()){
				status = sign;
			}
		}
		return status;
	}
}
