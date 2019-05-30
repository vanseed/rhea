package com.vanseed.rhea.service.user;

import com.vanseed.rhea.domain.model.User;

import java.util.Map;

/**
 * description
 *
 * @author leon
 * @date 2018/11/12
 */
public interface IUserService {

	public User newUser(User user);

	public User updateUser(User user);

	public User findUserById(Long id);
	
	public User findUserByName(String userName);

	public User register(String sessionId, Map<String, Object> paraMap);

	public User login(String sessionId, Map<String, Object> paraMap);
}
