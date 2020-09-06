package org.oaknorth.graphql.server.security;


import java.time.ZoneId;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static String getAuthorId() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(
                        user -> {
                            Object principle = user.getPrincipal();
                            if (principle instanceof String) {
                                return principle.toString();
                            } else {
                                return ((JWTUserDetails) (principle)).getUsername();
                            }
                        })
                .orElse("GUEST");
    }

    public static long getUserID() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object additionalDetails = authentication.getPrincipal();
        if (additionalDetails instanceof JWTUserDetails) {
            return ((JWTUserDetails) additionalDetails).getUserID();
        } else {
          throw new AccessDeniedException("Not Authenticated");
        }
    }

    public static String getUserZone() {
       return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(
                        user -> {
                            Object principle = user.getPrincipal();
                            if (principle instanceof String) {
                                return "UTC";
                            } else {
                                return ((JWTUserDetails) (principle)).getTimeZone();
                            }
                        })
                .orElse("UTC");
    }

    public static Stream<String> getGrantedAuthority(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority);
    }
}
