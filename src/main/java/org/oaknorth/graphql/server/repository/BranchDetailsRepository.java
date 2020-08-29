package org.oaknorth.graphql.server.repository;

import org.oaknorth.graphql.server.entity.BranchDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchDetailsRepository extends JpaRepository<BranchDetails,Long> {
}
