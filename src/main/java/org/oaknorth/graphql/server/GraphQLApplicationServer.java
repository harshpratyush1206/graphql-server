package org.oaknorth.graphql.server;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.oaknorth.graphql.server.entity.audit.AuditorAwareImpl;
import org.oaknorth.graphql.server.entity.audit.LocalDateTimeProvider;
import org.oaknorth.graphql.server.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


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
