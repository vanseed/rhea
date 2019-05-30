/**
 * 
 */
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

import com.vanseed.rhea.common.enums.AppType;
import com.vanseed.rhea.common.enums.DeviceType;
import com.vanseed.saturn.core.annotation.AEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author leon
 *
 */
@Entity
@Table(name = "iw_misc_app_version")
public class AppVersion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6751799122163485314L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", length = 11, nullable = false)
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("app_type")
	@AEnum(clz = AppType.class)
	@Column(name = "app_type")
	private Integer appType;
	
	@JsonProperty("device_type")
	@AEnum(clz = DeviceType.class)
	@Column(name = "device_type")
	private Integer deviceType;
	
	@JsonProperty("version")
	@Column(name = "version")
	private String version;
	
	@JsonProperty("pub_time")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pub_time")
	private Date pubTime;
	
	@JsonProperty("latest_flag")
	@Column(name = "latest_flag")
	private Integer latestFlag;
	
	@JsonProperty("valid_flag")
	@Column(name = "valid_flag")
	private Integer validFlag;

	@JsonProperty("app_path")
	@Column(name = "app_path")
	private String appPath;
	
	@JsonProperty("md5")
	@Column(name = "md5")
	private String md5;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}
	
	@JsonProperty("app_type_desc")
	public String getAppTypeDesc() {
		return appType==null?null:AppType.getStatusByIndex(appType).getName();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getPubTime() {
		return pubTime;
	}

	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}

	public Integer getLatestFlag() {
		return latestFlag;
	}

	public void setLatestFlag(Integer latestFlag) {
		this.latestFlag = latestFlag;
	}

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	
	@JsonProperty("device_type_desc")
	public String getDeviceTypeDesc() {
		return deviceType==null?null:DeviceType.getStatusByIndex(deviceType).getName();
	}

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	
}
