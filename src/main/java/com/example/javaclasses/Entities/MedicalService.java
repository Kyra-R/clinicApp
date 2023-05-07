package com.example.javaclasses.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;


@Entity
@Table(name = "medservices")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long baseprice;


    private Integer type; //терапевтические - 1, эстетические - 2 имплантация - 3 хирургия (удаление) - 4 ввести тип

    private String description;



    @OneToMany(orphanRemoval=true)
    @JoinColumn(name = "service_id")
    private List<Material> materialList;
}
