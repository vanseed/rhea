package com.vanseed.rhea.api.web;

import com.vanseed.rhea.domain.mybatis.UserMapper;
import com.vanseed.rhea.service.user.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TxnTests {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserMapper userMapper;

	@Before
	public void setUp() {
	}
	
	@Test
	public void contextLoads() {
	}

	@Test
	@Transactional
	@Rollback(false)
	public void test() throws Exception {
		
			//userService.testTxn();
			//AdminUser acct = adminUserMapper.findByName("aaa");
			//AdminUser userA = adminUserMapper.findByName("BBB");

		
		//Assert.assertEquals("aaa", sampleService.findSampleByName("aaa").getName());
	}
}
