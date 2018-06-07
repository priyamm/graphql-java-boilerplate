/**
 * 
 */
package com.abcplusd.masterapi.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.abcplusd.masterapi.service.datafetcher.UserDetailsDataFetcher;
import com.abcplusd.masterapi.service.datafetcher.UsersDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

/**
 * @author priyamgupta
 *
 */

@Service
public class GraphQLService {

//	@Value("classpath:userschema.graphqls")
//	Resource resource;
	
	Resource classPathResource;

	private GraphQL build;

	@Autowired
	private UsersDataFetcher usersDataFetcher;
	
	@Autowired
	private UserDetailsDataFetcher userDetailsDataFetcher;

	@PostConstruct
	private void loadSchema() throws IOException {
		// loading the schema

		// String schema = "type Query{hello: String}";
//		File schemaFile = resource.getFile();
//		Resource esources.getResource("classpath:schema.graphqls")
	    classPathResource = new ClassPathResource("userschema.graphqls");
	    InputStream inputStream = classPathResource.getInputStream();
	    File somethingFile = File.createTempFile("test", ".graphqls");
	    FileUtils.copyInputStreamToFile(inputStream, somethingFile);
	        
//		File schemaFile = resource.getInputStream();

		// parse schema
		SchemaParser schemaParser = new SchemaParser();
		TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(somethingFile);
		
		// RuntimeWiring runtimeWiring = newRuntimeWiring()
		// .type("Query", builder -> builder.dataFetcher("hello", new
		// StaticDataFetcher("world")))
		// .build();

		RuntimeWiring runtimeWiring = buildRuntimeWiring();

		SchemaGenerator schemaGenerator = new SchemaGenerator();
		GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

		build = GraphQL.newGraphQL(graphQLSchema).build();
		// ExecutionResult executionResult = build.execute("{hello}");
	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
						.dataFetcher("users", usersDataFetcher)
						.dataFetcher("userDetails", userDetailsDataFetcher))
				.type("User", typeWiring -> typeWiring
						.dataFetcher("details", userDetailsDataFetcher))
				.build();
	}

	public GraphQL getGraphQL() {
		return build;
	}

}
