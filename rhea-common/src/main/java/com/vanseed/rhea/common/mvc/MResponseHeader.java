/**
 * 
 */
package com.vanseed.rhea.common.mvc;

/**
 * @author leon
 * 
 */
public class MResponseHeader {
	private Integer clientType;

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	@Override
	public String toString() {
		return "clientType:" + clientType ;
	}
}
