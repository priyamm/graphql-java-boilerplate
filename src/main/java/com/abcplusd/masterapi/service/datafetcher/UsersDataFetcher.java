/**
 * 
 */
package com.abcplusd.masterapi.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abcplusd.masterapi.model.User;
import com.abcplusd.masterapi.repository.UserRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author priyamgupta
 *
 */

@Component
public class UsersDataFetcher implements DataFetcher<List<User>>  {
	
	@Autowired
	private UserRepository userRepository;

	/* (non-Javadoc)
	 * @see graphql.schema.DataFetcher#get(graphql.schema.DataFetchingEnvironment)
	 */
	@Override
	public List<User> get(DataFetchingEnvironment environment) {
		// TODO Auto-generated method stub
		
		String id = environment.getArgument("id");
		return userRepository.findUserList();
	}

}
