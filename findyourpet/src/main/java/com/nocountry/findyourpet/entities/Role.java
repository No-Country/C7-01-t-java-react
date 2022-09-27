package com.nocountry.findyourpet.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "id_role")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;



}
