package org.oaknorth.graphql.server.service;

import org.oaknorth.graphql.server.entity.BankDetails;
import org.oaknorth.graphql.server.repository.BankDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService{

    @Autowired
    private BankDetailsRepository bankDetailsRepository;

    @Override
    public BankDetails bankDetails(String accountNumber){
        return bankDetailsRepository.findByAccountNumber(accountNumber).orElse(null);
    }

    @Override
    public List<BankDetails> bankDetailsList(){
        return bankDetailsRepository.findAll();
    }
}
