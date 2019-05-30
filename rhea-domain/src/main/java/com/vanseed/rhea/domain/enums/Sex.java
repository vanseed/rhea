package com.vanseed.rhea.domain.enums;

import com.vanseed.saturn.core.IEnum;

public enum Sex implements IEnum {
	UNKOWN(0,"保密"),
	MAN(1,"男"),
	WOMAN(2,"女");
	
	private int index;
	private String name;
	
	private Sex(int index, String name) {
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
	
	public static final Sex getStatusByIndex(long index){
		Sex status = null;
		for(Sex sex :Sex.values()){
			if(index == sex.getIndex()){
				status = sex;
			}
		}
		return status;
	}
}
