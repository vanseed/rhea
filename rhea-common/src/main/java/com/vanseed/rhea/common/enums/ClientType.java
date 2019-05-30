package com.vanseed.rhea.common.enums;

import com.ebtrust.iwealth.core.IEnum;

public enum ClientType implements IEnum
{
	APP(1,"移动端"),
	WEB(2,"网页"),
	MINA(3,"小程序");	//小程序


	private int index;
	private String name;
	
	private ClientType(int index, String name) {
		this.index = index;
		this.name = name;
	}

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public static final ClientType getStatusByIndex(int index){
		ClientType clientType = null;
		for(ClientType client :ClientType.values()){
			if(index == client.getIndex()){
				clientType = client;
			}
		}
		return clientType;
	}
}
