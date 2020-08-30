package org.oaknorth.graphql.server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oaknorth.graphql.server.entity.Users;




@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {

    private String firstName;

    private String lastName;

    private String email;

    private String contact;

    private String password;

    private Users.UserStatus status;

    private Users.UserType userType;

    private String city;

    private String country;

    private String street;

    private String zip;
}
