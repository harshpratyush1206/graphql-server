package org.oaknorth.graphql.server.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.oaknorth.graphql.server.entity.audit.Auditable;
import org.oaknorth.graphql.server.models.UserModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    public Users() {
    }

    public Users(String firstName, String lastName,
                 String email, String contact,
                 String password,
                 UserStatus status,
                 UserType userType,
                 String city,
                 String country,
                 String street,
                 String zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.status = status;
        this.userType = userType;
        this.city = city;
        this.country = country;
        this.street = street;
        this.zip = zip;
    }

    public static Users map(UserModel userModel){
        return new Users(userModel.getFirstName(),userModel.getLastName(),userModel.getEmail(),userModel.getContact(),
                userModel.getPassword(),userModel.getStatus(),userModel.getUserType(),userModel.getCity(),
                userModel.getCountry(),userModel.getStreet(),userModel.getZip());
    }
}
