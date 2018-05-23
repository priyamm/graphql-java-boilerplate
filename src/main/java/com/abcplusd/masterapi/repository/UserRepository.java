/**
 * 
 */
package com.abcplusd.masterapi.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.abcplusd.masterapi.model.User;

/**
 * @author priyamgupta
 *
 */
@Repository
public class UserRepository {

	public List<User> findUserList() {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://localhost:8080/test/user";
		User[] userList = restTemplate.getForObject(resourceUrl, User[].class);
		return Arrays.asList(userList);
	}

}
