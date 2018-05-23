/**
 * 
 */
package com.abcplusd.masterapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcplusd.masterapi.service.GraphQLService;

import graphql.ExecutionResult;

/**
 * @author priyamgupta
 *
 */

@RestController
public class UserResource {

	@Autowired
	GraphQLService graphQLService;

	@PostMapping(value = "user")
	public ResponseEntity<Object> getUsers(@RequestBody String query) {
		ExecutionResult execute = graphQLService.getGraphQL().execute(query);
		return new ResponseEntity<>(execute, HttpStatus.OK);
	}

}
