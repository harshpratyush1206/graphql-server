package org.oaknorth.graphql.server.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.oaknorth.graphql.server.entity.BankDetails;
import org.oaknorth.graphql.server.exception.ValidationFailedException;
import org.oaknorth.graphql.server.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    @Autowired
    private BankService bankService;

    public List<BankDetails> allBank() {
            return bankService.bankDetailsList();
    }

    public BankDetails bank(String accountNumber) {
            return bankService.bankDetails(accountNumber).orElseThrow(()-> new ValidationFailedException("Bank details Not Found"));
        }
}
