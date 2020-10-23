package org.oaknorth.graphql.server.service;

import org.oaknorth.graphql.server.entity.BranchDetails;

import java.util.List;
import java.util.Optional;

public interface BranchService {

    BranchDetails createBranch(BranchDetails branchDetails);

    Optional<BranchDetails> getBranchByCode(String branchCode);

    List<BranchDetails> getAllBranches();
}
