package org.oaknorth.graphql.server.service;

import org.oaknorth.graphql.server.entity.Users;

import javax.validation.Valid;

public interface UserService {
     Users createUser(@Valid  Users users);
}