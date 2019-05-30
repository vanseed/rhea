package com.vanseed.rhea.domain.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vanseed.rhea.domain.enums.SmsVerifyStatus;
import com.vanseed.rhea.domain.enums.SmsVerifyType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vanseed.saturn.core.annotation.AEnum;


@Entity
@Table(name = "iw_misc_sms_verify")
public class SmsVerify implements Serializable {

	private static final long serialVersionUID = 7675066714054533179L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",nullable=false,length=20)
	private Long id;
	
	@Deprecated
	@JsonProperty("user_id")
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name="mobile",nullable=false,length=20)
	private String mobile;
	
	@Column(name="code",nullable=false,length=6)
	private String code;
	
	@JsonProperty("send_time")
	@Column(name = "send_time",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendTime;
	
	@JsonProperty("verify_time")
	@Column(name = "verify_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date verifyTime;
	
	@Column(name="verify_type",nullable=false,length=11,columnDefinition="INT default 0")
	@AEnum(clz= SmsVerifyType.class)
	private int verifyType;
	
	@Column(name="channel",nullable=false,length=11,columnDefinition="INT default 0")
	private int channel;
	
	@Column(name="verify_status",nullable=false,length=11,columnDefinition="INT default 0")
	@AEnum(clz= SmsVerifyStatus.class)
	private int verifyStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonIgnore
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@JsonIgnore
	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public int getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(int verifyType) {
		this.verifyType = verifyType;
	}

	public int getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(int verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	@JsonProperty("sms_use_status_desc")
	public String getSmsUseStatusDesc(){
		return SmsVerifyStatus.getStatusByIndex(verifyStatus).getName();
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public String getSmsVerifyTypeDesc() {
		return SmsVerifyType.getStatusByIndex(verifyType).getName();
	}
	
	
}
