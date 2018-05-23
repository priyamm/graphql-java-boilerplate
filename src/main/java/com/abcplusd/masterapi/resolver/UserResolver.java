/**
 * 
 */
package com.abcplusd.masterapi.resolver;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abcplusd.masterapi.helpers.JSONUtil;
import com.abcplusd.masterapi.model.Details;
import com.abcplusd.masterapi.model.User;
import com.abcplusd.masterapi.repository.DetailsRepository;
import com.coxautodev.graphql.tools.GraphQLResolver;

/**
 * @author priyamgupta
 *
 */
public class UserResolver implements GraphQLResolver<User> {
	@Autowired
	private DetailsRepository detailsRepository;
	
	public List<Details> details(User user) {
		System.out.println("---" + JSONUtil.objectToJSON(user));
		return detailsRepository.findDetailsList(user.get_id());
	}
}
