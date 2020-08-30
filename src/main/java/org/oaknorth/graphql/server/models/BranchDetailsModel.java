package org.oaknorth.graphql.server.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchDetailsModel {

    private String branchCode;

    private String city;

    private String country;

    private String street;

    private String zip;
}
