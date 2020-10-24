package org.oaknorth.graphql.server.service;

import lombok.extern.slf4j.Slf4j;
import org.oaknorth.graphql.server.entity.BankDetails;
import org.oaknorth.graphql.server.entity.BranchDetails;
import org.oaknorth.graphql.server.entity.Users;
import org.oaknorth.graphql.server.exception.ValidationFailedException;
import org.oaknorth.graphql.server.models.BankDetailsModel;
import org.oaknorth.graphql.server.repository.BankDetailsRepository;
import org.oaknorth.graphql.server.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BankServiceImpl implements BankService{

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    @Autowired
    private BranchService branchService;

    @Autowired
    private UserService userService;

    @Override
    public Optional<BankDetails> bankDetails(String accountNumber){
        return bankDetailsRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public List<BankDetails> bankDetailsList(){
        if(SecurityUtil.getGrantedAuthority().anyMatch(Users.UserType.BANKAPP_ACCESS::contains))
            return bankDetailsRepository.findAll();
        else
            return  bankDetailsRepository.findByUserId(SecurityUtil.getUserID());
    }

    @Override
    @Transactional
    public BankDetails createBank(BankDetailsModel bankDetailsModel) {
        BankDetails bankDetails =  new BankDetails();
        log.info("Creating bank account with number {} ",bankDetailsModel.getAccountNumber());
        if(this.bankDetails(bankDetailsModel.getAccountNumber()).isPresent()){
            throw new ValidationFailedException("Bank details already present");
        }

        bankDetails.setBranchCode(branchService.getBranchByCode(bankDetailsModel.getBranchCode()).
                map(BranchDetails::getBranchCode).
                orElseThrow(()-> new ValidationFailedException("Branch Not Found")));

        bankDetails.setUser(userService.findById(bankDetailsModel.getUserId()).
                orElseThrow(()-> new ValidationFailedException("User not found")));

        bankDetails.setAccountStatus(BankDetails.AccountStatus.OPENED);

        bankDetails.setAccountNumber(bankDetailsModel.getAccountNumber());

        return bankDetailsRepository.save(bankDetails);
    }
}
