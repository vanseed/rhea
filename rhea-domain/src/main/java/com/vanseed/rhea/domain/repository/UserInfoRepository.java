package com.vanseed.rhea.domain.repository;

import com.vanseed.rhea.domain.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * description
 *
 * @author leon
 * @date 2019/04/09
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> ,JpaSpecificationExecutor<UserInfo>{

    public UserInfo findByUserId(Integer userId);

}
