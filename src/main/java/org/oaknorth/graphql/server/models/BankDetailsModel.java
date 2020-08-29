package org.oaknorth.graphql.server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankDetailsModel {

    private String accountNumber;

    private String branchCode;

    private long userId;

}
