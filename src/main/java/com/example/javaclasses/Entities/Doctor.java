package com.example.javaclasses.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "doctors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String phone_number;

    @Column
    private String specialization;

    @Column
    private Integer type; //терапевтические - 1, эстетические - 2 имплантация - 3 хирургия (удаление) - 4


}
