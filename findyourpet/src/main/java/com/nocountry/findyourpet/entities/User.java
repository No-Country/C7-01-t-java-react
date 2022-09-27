package com.nocountry.findyourpet.entities;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
@SQLDelete(sql = "UPDATE user SET soft_delete = true WHERE id = ?")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @ManyToOne(targetEntity = Role.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role", insertable = false, updatable = false)
    private Role role;

    @Column(name = "id_role")
    private String idRole;

    @Column(nullable = false)
    private String phone;

    private String facebookAccount;

    @Column(name = "soft_delete")
    private Boolean softDelete;

    @CreatedDate
    @Column(columnDefinition = "create_On")
    private Timestamp createOn;
}
