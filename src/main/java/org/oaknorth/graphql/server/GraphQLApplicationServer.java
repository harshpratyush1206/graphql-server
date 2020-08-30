package org.oaknorth.graphql.server;

import org.oaknorth.graphql.server.entity.audit.AuditorAwareImpl;
import org.oaknorth.graphql.server.entity.audit.LocalDateTimeProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
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
