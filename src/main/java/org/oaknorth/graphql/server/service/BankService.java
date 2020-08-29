package org.oaknorth.graphql.server.service;

import org.oaknorth.graphql.server.entity.BankDetails;

import java.util.List;

public interface BankService {
    BankDetails bankDetails(String accountNumber);

    List<BankDetails> bankDetailsList();
}
