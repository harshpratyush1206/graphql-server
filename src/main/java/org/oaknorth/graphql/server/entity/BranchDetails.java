package org.oaknorth.graphql.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.oaknorth.graphql.server.entity.audit.Auditable;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "branch_details")
public class BranchDetails extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "branch_code", unique = true, nullable = false)
    private String branchCode;

    private String city;

    private String country;

    private String street;

    private String zip;
}
