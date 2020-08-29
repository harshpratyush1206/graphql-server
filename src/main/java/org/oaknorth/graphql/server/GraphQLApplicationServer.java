package org.oaknorth.graphql.server;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.oaknorth.graphql.server.entity.audit.AuditorAwareImpl;
import org.oaknorth.graphql.server.entity.audit.LocalDateTimeProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing(
		auditorAwareRef = "auditorAware",
		dateTimeProviderRef = "dateTimeProvider",
		modifyOnCreate = false)
public class GraphQLApplicationServer {

	public static void main(String[] args) {
		SpringApplication.run(GraphQLApplicationServer.class, args);
	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

	@Bean
	public DateTimeProvider dateTimeProvider() {
		return new LocalDateTimeProvider();
	}

}
