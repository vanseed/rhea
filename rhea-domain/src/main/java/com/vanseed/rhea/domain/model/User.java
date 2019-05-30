package com.vanseed.rhea.domain.model;

import com.vanseed.rhea.domain.enums.UserStatus;
import com.vanseed.rhea.domain.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vanseed.saturn.core.annotation.AEnum;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户基础信息
 *
 * @author leon
 * @date 2019/04/09
 */
@Entity
@Table(name = "iw_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",length=20,nullable=false)
    private long id;

    @JsonProperty("user_type")
    @Column(name="user_type",length=11,nullable=false,columnDefinition="INT default 0")
    @AEnum(clz= UserType.class)
    private Integer userType;

    @JsonProperty("user_name")
    @Column(name="user_name",length=50)
    private String userName;

    @JsonProperty("display_name")
    @Column(name="display_name",length=50)
    private String displayName;

    @JsonProperty("user_code")
    @Column(name="user_code",length=50)
    private String userCode;

    @JsonProperty("password")
    @Column(name="password",length=50)
    private String password;

    @JsonProperty("mobile")
    @Column(name="mobile",length=50)
    private String mobile;

    @JsonProperty("invitation_User_code")
    @Column(name="invitation_User_code",length=50)
    private String invitationUserCode;

    @JsonProperty("reg_date")
    @Column(name = "reg_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @JsonProperty("user_status")
    @Column(name="user_status",length=11,nullable=false,columnDefinition="INT default 0")
    @AEnum(clz= UserStatus.class)
    private Integer userStatus;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getInvitationUserCode() {
        return invitationUserCode;
    }

    public void setInvitationUserCode(String invitationUserCode) {
        this.invitationUserCode = invitationUserCode;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
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
