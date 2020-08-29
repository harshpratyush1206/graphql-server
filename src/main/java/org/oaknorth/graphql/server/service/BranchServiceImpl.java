package org.oaknorth.graphql.server.service;

import org.oaknorth.graphql.server.entity.BranchDetails;
import org.oaknorth.graphql.server.exception.ValidationFailedException;
import org.oaknorth.graphql.server.repository.BranchDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService{

    @Autowired
    private BranchDetailsRepository branchDetailsRepository;

    @Override
    public BranchDetails createBranch(BranchDetails branchDetails) {

        if(getBranchByCode(branchDetails.getBranchCode()).isPresent()){
            throw new ValidationFailedException("Branch already Present");
        }
        return branchDetailsRepository.save(branchDetails);
    }

    @Override
    public Optional<BranchDetails> getBranchByCode(String branchCode){
        return branchDetailsRepository.findByBranchCode(branchCode);
    }
}
