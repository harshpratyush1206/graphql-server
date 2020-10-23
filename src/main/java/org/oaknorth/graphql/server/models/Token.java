package org.oaknorth.graphql.server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oaknorth.graphql.server.entity.Users;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    public String token;

    private LocalDateTime expiresOn;

    private Users.UserType userType;
}
