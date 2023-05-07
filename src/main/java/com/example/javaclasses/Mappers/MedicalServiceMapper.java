package com.example.javaclasses.Mappers;


import com.example.javaclasses.DTOs.MedicalServiceDTO;

import com.example.javaclasses.Entities.MedicalService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class MedicalServiceMapper {


    public MedicalService toMedicalService(MedicalServiceDTO medicalServiceDTO) {
        MedicalService medicalService = new MedicalService();
        medicalService.setName(medicalServiceDTO.getName());
        medicalService.setBaseprice(medicalServiceDTO.getBaseprice());
        medicalService.setDescription(medicalServiceDTO.getDescription());
        medicalService.setType(medicalServiceDTO.getType());


        return medicalService;
    }

    public MedicalServiceDTO toMedicalServiceDTO(MedicalService medicalService) {
        MedicalServiceDTO medicalServiceDTO = new MedicalServiceDTO();
        medicalServiceDTO.setName(medicalService.getName());
        medicalServiceDTO.setBaseprice(medicalService.getBaseprice());
        medicalServiceDTO.setDescription(medicalService.getDescription());
        medicalServiceDTO.setType(medicalService.getType());
        medicalServiceDTO.setId(medicalService.getId());
        return medicalServiceDTO;
    }
}
