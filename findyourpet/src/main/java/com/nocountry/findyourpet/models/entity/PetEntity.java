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
public class PetEntity {

    @Id
    @GeneratedValue
    @Column(name = "id_pet")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity owner;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String photo;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String species;
    @Column(nullable = false)
    private String sex;
    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private Date date;

    @Column(name = "soft_delete")
    private Boolean softDelete;

    @CreatedDate
    @Column(columnDefinition = "create_On")
    private Timestamp createOn;

    @Column(nullable = false)
    private String tail;
    @Column(nullable = false)
    private String ears;



}
