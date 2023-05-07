package com.example.javaclasses.Mappers;

import com.example.javaclasses.DTOs.DoctorDTO;
import com.example.javaclasses.Entities.Doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DoctorMapper {

    public Integer defineType(String specialization){
        if(Objects.equals(specialization, "Терапевтическая ст-ия")){
            return 1;
        }
        else if(Objects.equals(specialization, "Эстетическая ст-ия")){
            return 2;
        }
        else if(Objects.equals(specialization, "Имплантация зубов")){
            return 3;
        }
        else if(Objects.equals(specialization, "Хирургическая ст-ия")){
            return 4;
        }
        return 0;
    }



    public Doctor toDoctor(DoctorDTO doctorDTO) {


        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setSurname(doctorDTO.getSurname());
        doctor.setPhone_number(doctorDTO.getPhone_number());
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setType(defineType(doctorDTO.getSpecialization()));


        return doctor;

    }

    public DoctorDTO toDoctorDTO(Doctor doctor) {
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setName(doctor.getName());
        doctorDTO.setSurname(doctor.getSurname());
        doctorDTO.setPhone_number(doctor.getPhone_number());
        doctorDTO.setId(doctor.getId());
        doctorDTO.setSpecialization(doctor.getSpecialization());

        return doctorDTO;
    }
}
