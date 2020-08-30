package org.oaknorth.graphql.server.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


@Getter
public class JWTUserDetails extends User {
    private final long userID;

    public JWTUserDetails(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            long userId) {
        super(username, password, authorities);
        this.userID = userId;
    }

    public JWTUserDetails(
            String username,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities,
            long userId) {
        super(
                username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        this.userID = userId;
    }

}
