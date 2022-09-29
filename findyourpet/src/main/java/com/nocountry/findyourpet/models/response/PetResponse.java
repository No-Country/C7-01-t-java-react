package com.nocountry.findyourpet.models.response;

import lombok.Data;

@Data
public class PetResponse {
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
    private String tail;
    private String ears;
}
