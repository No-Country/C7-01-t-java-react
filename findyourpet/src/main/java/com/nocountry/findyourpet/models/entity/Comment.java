package com.nocountry.findyourpet.models.entity;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@SQLDelete(sql = "UPDATE comment SET soft_delete = true WHERE id = ?")
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "id_comment")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pet", insertable = false , updatable = false)
    private Pet pet;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String description;

    private String photo;

    @Column(name = "soft_delete")
    private Boolean softDelete;
    @CreatedDate
    @Column(columnDefinition = "create_On")
    private Timestamp createOn;

}
