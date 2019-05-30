package com.vanseed.rhea.common.enums;

import com.ebtrust.iwealth.core.IEnum;

public enum DeviceType implements IEnum {

	Other(0,"其他"),
	ANDROID(1,"android"),
	IOS(2,"ios"),
	PC(3,"pc");

	
	private int index;
	private String name;
	
	private DeviceType(int index, String name) {
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
	
	public static final DeviceType getStatusByIndex(int index){
		DeviceType status = null;
		for(DeviceType sign :DeviceType.values()){
			if(index == sign.getIndex()){
				status = sign;
			}
		}
		return status;
	}
}
