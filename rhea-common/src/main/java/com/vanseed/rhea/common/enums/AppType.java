package com.vanseed.rhea.common.enums;

import com.vanseed.saturn.core.IEnum;

public enum AppType implements IEnum {

	C(1,"客户版"),
	T(2,"理财经理版");


	private int index;
	private String name;

	private AppType(int index, String name) {
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
	
	public static final AppType getStatusByIndex(int index){
		AppType type = null;
		for(AppType sign : AppType.values()){
			if(index == sign.getIndex()){
				type = sign;
			}
		}
		return type;
	}
}
