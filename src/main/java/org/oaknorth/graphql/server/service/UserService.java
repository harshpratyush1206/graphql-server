package org.oaknorth.graphql.server.service;

import org.oaknorth.graphql.server.entity.Users;

import javax.validation.Valid;
import java.util.Optional;

public interface UserService {
     Users createUser(@Valid  Users users);

     Optional<Users> findById(Long id);
}
