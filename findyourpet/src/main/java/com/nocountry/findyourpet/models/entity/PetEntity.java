package com.nocountry.findyourpet.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE pet SET soft_delete = true WHERE id = ?")
@Table(name = "pet")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_pet")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private UserEntity owner;

    private String name;

    private String photo;

    private Integer age;

    private String description;

    private String color;

    private String location;

    private String species;

    private String sex;

    private String size;

    private String date;

    @Column(name = "soft_delete")
    private Boolean softDelete;

    @CreationTimestamp
    private Timestamp createOn;

    private String tail;

    private String ears;



}
