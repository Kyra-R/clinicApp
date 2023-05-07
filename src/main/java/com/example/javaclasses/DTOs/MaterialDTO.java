package com.example.javaclasses.DTOs;
import com.example.javaclasses.Entities.Views;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
public class MaterialDTO {
    @JsonView(Views.Internal.class)
    private Long id;

    @JsonView(Views.Public.class)
    private String name;

    @JsonView(Views.Public.class)
    private Long price;

    @JsonView(Views.Internal.class)
    private Long service_id;
}
