package org.oaknorth.graphql.server.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


@Getter
public class JWTUserDetails extends User {
    private final long userID;
    private final String token;

    public JWTUserDetails(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            long userId, String token) {
        super(username, password, authorities);
        this.userID = userId;
        this.token = token;
    }

    public JWTUserDetails(
            String username,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities,
            long userId, String token) {
        super(
                username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        this.userID = userId;
        this.token = token;
    }

}
