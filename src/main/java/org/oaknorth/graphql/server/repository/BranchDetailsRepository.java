package org.oaknorth.graphql.server.repository;

import org.oaknorth.graphql.server.entity.BranchDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchDetailsRepository extends JpaRepository<BranchDetails,Long> {
    Optional<BranchDetails> findByBranchCode(String branchCode);
}
