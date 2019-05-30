package com.vanseed.rhea.domain.repository;

import com.vanseed.rhea.domain.model.SmsVerify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * description
 *
 * @author leon
 * @date 2019/04/09
 */
public interface SmsVerifyRepository extends JpaRepository<SmsVerify, Long> ,JpaSpecificationExecutor<SmsVerify>{

    public List<SmsVerify> findByMobileAndVerifyStatusAndVerifyTypeOrderBySendTimeDesc(String mobile,int status,int type);

    @Query("from SmsVerify s where s.mobile=?1 and s.sendTime>current_date")
    public List<SmsVerify> findByMobieAndCurrentDate(String mobile);
}
