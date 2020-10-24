package org.oaknorth.graphql.server.entity;

import com.google.common.collect.ImmutableSet;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.oaknorth.graphql.server.entity.audit.Auditable;
import org.oaknorth.graphql.server.models.UserModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user_details")
@NoArgsConstructor
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

    @Column(name = "password_expiry", nullable = false)
    private LocalDateTime passwordExpiresOn;

    @Column(name = "time_zone")
    private String timeZone;


    public enum UserStatus {
        ACTIVE,EXPIRED
    }

    public enum UserType {
        USER,
        ADMIN,
        BANKER;

        public static final Set<String> BANKAPP_ACCESS =
                ImmutableSet.of(ADMIN.toString(), BANKER.toString());
    }
    public Users(String firstName, String lastName,
                 String email, String contact,
                 String password,
                 UserStatus status,
                 String city,
                 String country,
                 String street,
                 String zip,String timeZone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.status = status;
        this.city = city;
        this.country = country;
        this.street = street;
        this.zip = zip;
        this.timeZone = timeZone;
    }

    public static Users map(UserModel userModel){

        Users user = new Users(userModel.getFirstName(),userModel.getLastName(),userModel.getEmail(),userModel.getContact(),
                userModel.getPassword(),userModel.getStatus(),userModel.getCity(),
                userModel.getCountry(),userModel.getStreet(),userModel.getZip(),userModel.getTimeZone());
        user.setPasswordExpiresOn(LocalDateTime.now().plus(2, ChronoUnit.YEARS));
        return user;
    }
}
