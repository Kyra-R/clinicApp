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

    @Column
    private Integer type; 
    @Column
    private String description;



    @OneToMany(orphanRemoval=true)
    @JoinColumn(name = "service_id")
    private List<Material> materialList;
}
