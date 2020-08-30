package org.oaknorth.graphql.server.security;

import lombok.RequiredArgsConstructor;
import org.oaknorth.graphql.server.exception.BadTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Pattern BEARER_PATTERN = Pattern.compile("^Bearer (.+?)$");

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            getToken(request)
                    .map(userDetailsService::loadUserByToken)
                    .map(userDetails -> JWTPreAuthenticationToken
                            .builder()
                            .principal(userDetails)
                            .details(new WebAuthenticationDetailsSource().buildDetails(request))
                            .build())
                    .ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));
        }catch (BadTokenException e) {
            response.sendError(401);
        }
        filterChain.doFilter(request, response);

    }

    private Optional<String> getToken(HttpServletRequest request) {
        return Optional
                .ofNullable(request.getHeader(AUTHORIZATION_HEADER))
                .filter(d-> !d.isEmpty())
                .map(BEARER_PATTERN::matcher)
                .filter(Matcher::find)
                .map(matcher -> matcher.group(1));
    }
}
