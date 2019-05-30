package com.vanseed.rhea.domain.repository;

import com.vanseed.rhea.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * description
 *
 * @author leon
 * @date 2019/04/09
 */
public interface UserRepository extends JpaRepository<User, Long> ,JpaSpecificationExecutor<User>{

    public User findByUserName(String userName);

    public User findByMobile(String mobile);

}
