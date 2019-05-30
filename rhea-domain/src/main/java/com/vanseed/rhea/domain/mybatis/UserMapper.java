package com.vanseed.rhea.domain.mybatis;

import com.github.pagehelper.Page;
import com.vanseed.rhea.domain.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Mapper
@Repository
public interface UserMapper {

    @Select("SELECT * FROM IW_USER WHERE USER_NAME = #{name}")
    User findByUserName(@Param("name") String name);
    
    @Select("SELECT * FROM IW_USER WHERE USER_STATUS = #{userStatus}")
    Page<User> findByStatusPaging(@Param("userStatus") Integer userStatus);

    @Insert("INSERT INTO OM_USER(name, amount, status, apply_date, create_time) VALUES(#{name},#{amount},1,CURDATE(),NOW())")
    int insert(@Param("name") String name, @Param("amount") BigDecimal amount);

    User findByMobile(@Param("mobile") String mobile, @Param("userStatus") int userStatus);

}