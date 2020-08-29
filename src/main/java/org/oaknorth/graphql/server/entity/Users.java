package org.oaknorth.graphql.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.oaknorth.graphql.server.entity.audit.Auditable;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_details")
public class Users extends Auditable implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", length = 60)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String contact;

    @Column(length = 100)
    private String password;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;


    private String city;

    private String country;

    private String street;

    private String zip;

    public enum UserStatus {
        ACTIVE,EXPIRED
    }

    public enum UserType {
        USER,
        ADMIN,
        BANKER
    }
}
