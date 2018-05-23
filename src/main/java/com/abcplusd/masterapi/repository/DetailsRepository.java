/**
 * 
 */
package com.abcplusd.masterapi.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.abcplusd.masterapi.model.Details;

/**
 * @author priyamgupta
 *
 */
@Repository
public class DetailsRepository {
	public List<Details> findDetailsList(String referral) {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://localhost:8080/test/details/" + referral;
		Details[] detailsList = restTemplate.getForObject(resourceUrl, Details[].class);
		return Arrays.asList(detailsList);
	}
}
