package com.vanseed.rhea.domain.model;

import com.vanseed.rhea.domain.enums.Career;
import com.vanseed.rhea.domain.enums.IDStatus;
import com.vanseed.rhea.domain.enums.Sex;
import com.vanseed.rhea.domain.enums.Education;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vanseed.saturn.core.annotation.AEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户扩展信息
 *
 * @author leon
 * @date 2019/04/09
 */
@Entity
@Table(name="iw_user_info")
public class UserInfo implements Serializable{

	private static final long serialVersionUID = -7020540258535614930L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id",nullable=false,length=20)
	private Long id;

	@JsonProperty("user_id")
	@Column(name="user_id",nullable=false)
	private Long userId;

	@JsonProperty("real_name")
	@Column(name="real_name")
	private String realName;

	@JsonProperty("id_no")
	@Column(name="id_no")
	private String idNo;

	@JsonProperty("id_status")
	@Column(name="id_status")
	@AEnum(clz= IDStatus.class)
	private Integer idStatus;

	@JsonProperty("sex")
	@Column(name="sex")
	@AEnum(clz= Sex.class)
	private Integer sex;

	@JsonProperty("education")
	@Column(name="education")
	@AEnum(clz= Education.class)
	private Integer education;

	@JsonProperty("career")
	@Column(name="career")
	@AEnum(clz= Career.class)
	private String career;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time")
	private Date updateTime;


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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
