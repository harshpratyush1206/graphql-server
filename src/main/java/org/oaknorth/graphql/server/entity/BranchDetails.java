package org.oaknorth.graphql.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.oaknorth.graphql.server.entity.audit.Auditable;
import org.oaknorth.graphql.server.models.BankDetailsModel;
import org.oaknorth.graphql.server.models.BranchDetailsModel;

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

    public BranchDetails() {
    }

    public BranchDetails(String branchCode, String city, String country, String street, String zip) {
        this.branchCode = branchCode;
        this.city = city;
        this.country = country;
        this.street = street;
        this.zip = zip;
    }

    public static BranchDetails map(BranchDetailsModel branchDetailsModel){
        return new BranchDetails( branchDetailsModel.getBranchCode(),
                branchDetailsModel.getCity(), branchDetailsModel.getCountry(),
                branchDetailsModel.getStreet(),branchDetailsModel.getZip());

    }
}
