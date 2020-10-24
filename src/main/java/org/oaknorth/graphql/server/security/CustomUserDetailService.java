package org.oaknorth.graphql.server.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.oaknorth.graphql.server.entity.Users;
import org.oaknorth.graphql.server.exception.BadTokenException;
import org.oaknorth.graphql.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {


    @Autowired
    private JWTVerifier verifier;

    @Autowired
    private UserService userService;

    @Autowired
    private Algorithm algorithm;

    @Autowired
    private SecurityProperties securityProperties;



    @Override
    public UserDetails loadUserByUsername(String username) {
        return userService
                .findByEmail(username)
                .map(this::getUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Username or password didn''t match"));
    }

    @Transactional
    public JWTUserDetails loadUserByToken(String token) {
        return getDecodedToken(token)
                .map(DecodedJWT::getSubject)
                .flatMap(userService::findByEmail)
                .map(this::getUserDetails)
                .orElseThrow(BadTokenException::new);
    }

    public Optional<DecodedJWT> getDecodedToken(String token) {
        try {
            return Optional.of(verifier.verify(token));
        } catch(JWTVerificationException|IllegalArgumentException ex) {
            return Optional.empty();
        }
    }

    @Transactional
    public Users getCurrentUser() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getName)
                .flatMap(userService::findByEmail)
                .orElse(null);
    }



    @Transactional
    public String getToken(Users user) {
        Instant now = Instant.now(Clock.systemUTC());
        Instant expiry = now.plus(securityProperties.getTokenExpiration());
        return JWT
                .create()
                .withIssuer(securityProperties.getIssuer())
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(expiry))
                .withSubject(user.getEmail())
                .sign(algorithm);
    }

    public boolean isAuthenticated() {
        return Optional
                .ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .filter(d-> !isAnonymous(d))
                .isPresent();
    }

    private boolean isAnonymous(Authentication authentication) {
        return authentication instanceof AnonymousAuthenticationToken;
    }



    private JWTUserDetails getUserDetails(Users user) {
        return new JWTUserDetails(user.getEmail(),user.getPassword(), true,true,
                user.getPasswordExpiresOn().isAfter(LocalDateTime.now()),true,Arrays.asList((GrantedAuthority
                ) () -> user.getUserType().name()),user.getId(), user.getTimeZone());
    }
}
