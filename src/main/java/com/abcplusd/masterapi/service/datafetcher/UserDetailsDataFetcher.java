/**
 * 
 */
package com.abcplusd.masterapi.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abcplusd.masterapi.model.Details;
import com.abcplusd.masterapi.model.User;
import com.abcplusd.masterapi.repository.DetailsRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

/**
 * @author priyamgupta
 *
 */
@Component
public class UserDetailsDataFetcher implements DataFetcher<List<Details>> {

	@Autowired
	private DetailsRepository detailsRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see graphql.schema.DataFetcher#get(graphql.schema.DataFetchingEnvironment)
	 */
	@Override
	public List<Details> get(DataFetchingEnvironment environment) {
		String id = environment.getArgument("id");
		if(id == null) {
			User user = (User)environment.getSource();
			id = user.get_id();
		}
		return detailsRepository.findDetailsList(id);
	}

}
