package org.oaknorth.graphql.server.security;

import lombok.Builder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class JWTPreAuthenticationToken extends PreAuthenticatedAuthenticationToken {

    @Builder
    public JWTPreAuthenticationToken(JWTUserDetails principal, WebAuthenticationDetails details) {
        super(principal, null, principal.getAuthorities());
        super.setDetails(details);
    }

    @Override
    public Object getCredentials() {
        return null;
    }
}
