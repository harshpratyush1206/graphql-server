package org.oaknorth.graphql.server.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;


@Getter
public class JWTUserDetails extends User {
    private final long userID;
    // -- user-- preference--//
    private final String timeZone;

    public JWTUserDetails(
            String username,
            String password,
            Collection<? extends GrantedAuthority> authorities,
            long userId, String timeZone) {
        super(username, password, authorities);
        this.userID = userId;
        this.timeZone = timeZone;
    }

    public JWTUserDetails(
            String username,
            String password,
            boolean enabled,
            boolean accountNonExpired,
            boolean credentialsNonExpired,
            boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities,
            long userId, String timeZone) {
        super(
                username,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        this.userID = userId;
        this.timeZone = timeZone;
    }

}
