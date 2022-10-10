package com.nocountry.findyourpet.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.findyourpet.utilities.Role;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE user SET soft_delete = true WHERE id = ?")
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_user")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    private String lastName;

    @Nullable
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PetEntity> pets;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String phone;

    private String facebookAccount;

    @Column(name = "soft_delete")
    private Boolean softDelete;

    @CreationTimestamp
    private Timestamp createOn;

}
