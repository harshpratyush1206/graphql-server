package org.oaknorth.graphql.server.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.oaknorth.graphql.server.entity.BankDetails;
import org.oaknorth.graphql.server.entity.BranchDetails;
import org.oaknorth.graphql.server.entity.Users;
import org.oaknorth.graphql.server.models.BankDetailsModel;
import org.oaknorth.graphql.server.models.BranchDetailsModel;
import org.oaknorth.graphql.server.models.UserModel;
import org.oaknorth.graphql.server.service.BankService;
import org.oaknorth.graphql.server.service.BranchService;
import org.oaknorth.graphql.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;


@Slf4j
@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private BankService bankService;

    public Users createUser(UserModel userModel){
        log.info("Inside create user mutation");
        return userService.createUser(Users.map(userModel));
    }

    public BranchDetails createBranch(BranchDetailsModel branchDetailsModel){
        return branchService.createBranch(BranchDetails.map(branchDetailsModel));
    }

    public BankDetails createBankDetails(@Valid BankDetailsModel bankDetails){
        return bankService.createBank(bankDetails);
    }
}
