package org.oaknorth.graphql.server.repository;

import org.oaknorth.graphql.server.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BankDetailsRepository extends JpaRepository<BankDetails,Long> {
    Optional<BankDetails> findByAccountNumber(String accountNumber);

    List<BankDetails> findByUserId(long userID);
}
