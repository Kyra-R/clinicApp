package com.example.javaclasses.DTOs;

import com.example.javaclasses.Entities.Views;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class MedicalServiceDTO {
    @JsonView(Views.Internal.class)
    private Long id;

    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private Long baseprice;

    @JsonView(Views.Public.class)
    private String description;

    @JsonView(Views.Internal.class)
    private Integer type;



}
