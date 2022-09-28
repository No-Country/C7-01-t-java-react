package com.nocountry.findyourpet.models.entity;

import com.nocountry.findyourpet.utilities.Role;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Data
@SQLDelete(sql = "UPDATE user SET soft_delete = true WHERE id = ?")
@Table(name = "user")
public class UserEntity {

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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private String phone;

    private String facebookAccount;

    @Column(name = "soft_delete")
    private Boolean softDelete;

    @CreatedDate
    @Column(columnDefinition = "create_On")
    private Timestamp createOn;

}
