package com.example.javaclasses.DTOs;
import com.example.javaclasses.Entities.Views;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class DoctorDTO {
    @JsonView(Views.Internal.class)
    private Long id;

    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private String surname;

    @JsonView(Views.Public.class)
    private String phone_number;

    @JsonView(Views.Public.class)
    private String specialization;
}
