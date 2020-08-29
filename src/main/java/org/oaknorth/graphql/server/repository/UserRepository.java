package org.oaknorth.graphql.server.repository;

import org.oaknorth.graphql.server.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
