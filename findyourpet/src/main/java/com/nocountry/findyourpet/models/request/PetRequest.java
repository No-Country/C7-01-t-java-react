package com.nocountry.findyourpet.models.request;

import lombok.Data;

import java.util.Date;
@Data
public class PetRequest {
    private String name;
    private String photo;
    private Integer age;
    private String description;
    private String color;
    private String location;
    private String species;
    private String sex;
    private String size;
    private Date date;
    private String tails;
    private String ears;
}
