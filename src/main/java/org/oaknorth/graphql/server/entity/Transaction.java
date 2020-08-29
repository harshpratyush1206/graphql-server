package org.oaknorth.graphql.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.oaknorth.graphql.server.entity.audit.Auditable;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "bank_transaction")
public class Transaction extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_number",referencedColumnName = "account_number",
            nullable = false,insertable = false,updatable = false)
    private BankDetails bankDetails;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "reversal_id")
    private Long reversalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reversal_id",referencedColumnName = "id",
            nullable = false,insertable = false,updatable = false)
    private Transaction reversalDetails;

    @Column(name = "to_account")
    private String toAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="to_account",referencedColumnName = "account_number",
            nullable = false,insertable = false,updatable = false)
    private BankDetails toBankDetails;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;


    public enum TransactionType{
        DEPOSIT,WITHDRAWAL,TRANSFER,REVERSAL
    }

}
