package org.oaknorth.graphql.server.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.oaknorth.graphql.server.entity.BankDetails;
import org.oaknorth.graphql.server.entity.BranchDetails;
import org.oaknorth.graphql.server.entity.Users;
import org.oaknorth.graphql.server.models.BankDetailsModel;
import org.oaknorth.graphql.server.service.BankService;
import org.oaknorth.graphql.server.service.BranchService;
import org.oaknorth.graphql.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MutationResolver implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private BankService bankService;

    public Users createUser( String firstName,  String lastName,
                                  String email,  String contact,
                                  String password,
                                  Users.UserStatus status,
                                  Users.UserType userType,
                                  String city,
                                  String country,
                                  String street,
                                  String zip){
        log.info("Inside create user mutation");
        Users users = new Users(firstName,lastName,email,contact,
                password,status,userType,city,country,street,zip);
        return userService.createUser(users);
    }

    public BranchDetails createBranch(String branchCode, String city, String country, String street, String zip){
        BranchDetails branchDetails = new BranchDetails( branchCode,  city,  country,  street,  zip);
        return branchService.createBranch(branchDetails);
    }

    public BankDetails createBankDetails(String accountNumber, String branchCode, Long userId){
        BankDetailsModel bankDetails = new BankDetailsModel(accountNumber,branchCode,userId);
        return bankService.createBank(bankDetails);
    }
}
