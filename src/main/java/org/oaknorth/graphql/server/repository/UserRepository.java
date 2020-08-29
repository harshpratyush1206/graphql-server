package org.oaknorth.graphql.server.repository;

import org.oaknorth.graphql.server.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmailIgnoreCase(String email);
}
