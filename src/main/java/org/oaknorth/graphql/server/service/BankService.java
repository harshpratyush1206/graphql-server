package org.oaknorth.graphql.server.service;

import org.oaknorth.graphql.server.entity.BankDetails;
import org.oaknorth.graphql.server.models.BankDetailsModel;

import java.util.List;
import java.util.Optional;

public interface BankService {
    Optional<BankDetails> bankDetails(String accountNumber);

    List<BankDetails> bankDetailsList();

    BankDetails createBank(BankDetailsModel bankDetails);
}
