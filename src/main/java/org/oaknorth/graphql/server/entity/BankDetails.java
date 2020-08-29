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
@Table(name = "bank_details")
public class BankDetails extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    @Column(name = "branch_code")
    private String branchCode;


    @ManyToOne
    @JoinColumn(name="branch_code",referencedColumnName = "branch_code",
            nullable = false,insertable = false,updatable = false)
    private BranchDetails branchDetails;

    @ManyToOne
    @JoinColumn(name="user_fk",nullable = false,insertable = false,updatable = false)
    private Users user;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;


    public enum AccountStatus{
        ACTIVE,CLOSED,OPENED
    }
}
