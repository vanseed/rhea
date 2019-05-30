package com.vanseed.rhea.api.web;

import com.vanseed.rhea.domain.enums.UserStatus;
import com.vanseed.rhea.domain.enums.UserType;
import com.vanseed.rhea.domain.model.User;
import com.vanseed.rhea.domain.mybatis.UserMapper;
import com.vanseed.rhea.domain.repository.UserInfoRepository;
import com.vanseed.rhea.domain.repository.UserRepository;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.vanseed.saturn.support.page.PageInfo;
import com.vanseed.saturn.support.util.EncryptUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTests {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;

    @Autowired
    private UserInfoRepository userInfoRepository;

	@Before
	public void setUp() {
	}
	
	@Test
	public void contextLoads() {
	}

	@Test
	@Transactional
    @Rollback(true)
	public void testJpa() throws Exception {
        User user = new User();
        user.setUserName("13800138001");
        user.setUserType(UserType.MOBILE.getIndex());
        user.setDisplayName("Test_B");
        user.setUserCode("1002");
        user.setPassword(EncryptUtils.MD5("password"));

        userRepository.save(user);

		
		Assert.assertEquals("1002", userRepository.findByUserName("13800138001").getUserCode());
	}


	@Test
	@Transactional
	@Rollback(false)
	public void testMybatis() throws Exception {
		//sampleMapper.insert("operation", new BigDecimal(11.11));
		User u1 = userMapper.findByMobile("13800138000", UserStatus.NORMAL.getIndex());

		Assert.assertEquals(u1.getDisplayName(), "Test_A");
	}
	
	//翻页测试
	@Test
	public void testPageing() throws Exception {
		PageHelper.startPage(1, 2, true);
		Page<User> listUser = userMapper.findByStatusPaging(1);
		PageInfo<User> pageInfo = new PageInfo<>(listUser);
		pageInfo.getTotalCounts();
		Assert.assertEquals(pageInfo.getTotalCounts(), 2l);
	}
	
	
//	//通过xml配置动态sql查询测试
//	@Test
//	public void findByMapping() throws Exception {
//		//sampleMapper.insert("operation", new BigDecimal(11.11));
//		Sample u = sampleMapper.findSampleMapping("operation",1);
//		Assert.assertEquals("operation", u.getName());
//	}

}
