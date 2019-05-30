package com.vanseed.rhea.api.web;

import com.vanseed.rhea.common.mvc.MSession;
import com.vanseed.rhea.service.SessionService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

	@Autowired
	private SessionService sessionService;

	@Before
	public void setUp() {
	}
	
	@Test
	public void contextLoads() {
	}

	@Test
	//@Rollback
	public void testSession() throws Exception {

		MSession session = new MSession();
		session.setSessionId("testSession_0001");
		session.setUserId(1l);
		//session.setLastAccessTime((new Date()).getTime());
		sessionService.setSession(session);
		
		Assert.assertEquals(Long.valueOf(1), sessionService.getSession("testSession_0001").getUserId());
	}
}
