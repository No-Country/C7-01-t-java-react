package com.nocountry.findyourpet.models.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@SQLDelete(sql = "UPDATE pet SET soft_delete = true WHERE id = ?")
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue
    @Column(name = "id_pet")
    private Long id;

    @ManyToOne(targetEntity = UserEntity.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "id_user")
    private Long idUser;

    private String name;
    private String photo;
    private Integer age;
    private String description;
    private String color;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String species;

    private String sex;
    private String size;
    private Date date;

    @Column(name = "soft_delete")
    private Boolean softDelete;

    @CreatedDate
    @Column(columnDefinition = "create_On")
    private Timestamp createOn;

    private String state;
    private String tail;
    private String ears;



}
