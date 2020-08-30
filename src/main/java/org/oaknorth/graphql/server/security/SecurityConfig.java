package org.oaknorth.graphql.server.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {


    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public Algorithm jwtAlgorithm() {
        return Algorithm.HMAC512(securityProperties.getSecret());
    }

    @Bean
    public JWTVerifier verifier(Algorithm algorithm) {
        return JWT
                .require(algorithm)
                .withIssuer(securityProperties.getIssuer())
                .build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(securityProperties.getPasswordStrength());
    }

    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailService userService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
