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
@SQLDelete(sql = "UPDATE comment SET soft_delete = true WHERE id = ?")
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pet", insertable = false , updatable = false)
    private PetEntity petEntity;

    private String firstName;

    private String lastName;

    private String email;

    private String description;

    private String photo;

    @Column(name = "soft_delete")
    private Boolean softDelete;

    @CreationTimestamp
    private Timestamp createOn;

}
