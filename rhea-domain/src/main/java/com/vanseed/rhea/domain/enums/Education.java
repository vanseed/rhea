package com.vanseed.rhea.domain.enums;

import com.vanseed.saturn.core.IEnum;

public enum Education implements IEnum {
	PRIMARY (1,"小学"),
	MIDDLE (2,"初中"),
	HIGH(3,"高中"),
	UNIVERSITY (4,"本科"),
	MASTER(5,"硕士"),
	DOCTORATE(6,"博士");
	
	private int index;
	private String name;
	
	private Education(int index, String name) {
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
	
	public static final Education getStatusByIndex(Integer index){
		Education status = null;
		for(Education sex :Education.values()){
			if(sex.getIndex() == index){
				status = sex;
			}
		}
		return status;
	}
}
