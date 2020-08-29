package org.oaknorth.graphql.server.service;

import org.oaknorth.graphql.server.entity.Users;
import org.oaknorth.graphql.server.exception.ValidationFailedException;
import org.oaknorth.graphql.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository  userRepository;

    @Override
    @Transactional
    public Users createUser(Users users) {
        if(userRepository.findByEmailIgnoreCase(users.getEmail()).isPresent()){
            throw new ValidationFailedException("Email Id already present");
        }
        return userRepository.save(users);
    }


    @Override
    public Optional<Users> findById(Long id){
        return userRepository.findById(id);
    }
}
