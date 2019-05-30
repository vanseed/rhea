package com.vanseed.rhea.domain.enums;

import com.vanseed.saturn.core.IEnum;

public enum Career implements IEnum {
	STUDENT (1,"学生"),
	ENGINEERING (2,"工程师");
	
	private int index;
	private String name;
	
	private Career(int index, String name) {
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
	
	public static final Career getStatusByIndex(long index){
		Career status = null;
		for(Career sex :Career.values()){
			if(index == sex.getIndex()){
				status = sex;
			}
		}
		return status;
	}
}
