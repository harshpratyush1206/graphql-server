package org.oaknorth.graphql.server.repository;

import org.oaknorth.graphql.server.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
