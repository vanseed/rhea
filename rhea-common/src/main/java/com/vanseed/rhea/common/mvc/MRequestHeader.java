/**
 * 
 */
package com.vanseed.rhea.common.mvc;

import com.vanseed.rhea.common.enums.ClientType;
import com.vanseed.rhea.common.enums.DeviceType;
import com.vanseed.saturn.core.annotation.AEnum;


/**
 * @author leon
 *
 */
public class MRequestHeader {
	@AEnum(clz= ClientType.class)
	private Integer clientType;				//客户端类型（web,app,小程序等）
	private String clientVersion = "";	//客户端版本
	private String sessionId;				//sessionid
	@AEnum(clz= DeviceType.class)
	private Integer deviceType;				//设备类型（安卓,ios,pc）
	private String deviceId = "";			//设备id
	private String protocolCode = "";			//协议号，根据协议分发不同的处理器
	private String protocolVersion = "";	//协议版本，根据协议分发不同的处理器
	private String language = "zh_CN"; 		//语言

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getProtocolCode() {
		return protocolCode;
	}

	public void setProtocolCode(String protocolCode) {
		this.protocolCode = protocolCode;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "";
	}
}
