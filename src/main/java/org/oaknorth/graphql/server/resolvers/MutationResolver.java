package org.oaknorth.graphql.server.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.oaknorth.graphql.server.entity.Users;
import org.oaknorth.graphql.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    public Users createUser( String firstName,  String lastName,
                                  String email,  String contact,
                                  String password,
                                  Users.UserStatus status,
                                  Users.UserType userType,
                                  String city,
                                  String country,
                                  String street,
                                  String zip){
        log.info("Inside create user mutation");
        Users users = new Users(firstName,lastName,email,contact,
                password,status,userType,city,country,street,zip);
        return userService.createUser(users);
    }
}
