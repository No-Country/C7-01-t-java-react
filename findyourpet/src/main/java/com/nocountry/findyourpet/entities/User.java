package com.nocountry.findyourpet.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private Long id;

    private String email;
    private String password;
    private String name;
    private String lastName;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Role role;
    private String phone;
    private String facebookAccount;
    private Boolean isDeleted;

    @Temporal(TIMESTAMP)
    private Date createdOn;
}
