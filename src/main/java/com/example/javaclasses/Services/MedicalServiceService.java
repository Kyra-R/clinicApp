package com.example.javaclasses.Services;

import com.example.javaclasses.DTOs.DoctorDTO;
import com.example.javaclasses.DTOs.MaterialDTO;
import com.example.javaclasses.DTOs.MedicalServiceDTO;
import com.example.javaclasses.Entities.Material;
import com.example.javaclasses.Entities.MedicalService;
import com.example.javaclasses.Errors.ElementNotFoundException;
import com.example.javaclasses.Mappers.DoctorMapper;
import com.example.javaclasses.Mappers.MaterialMapper;
import com.example.javaclasses.Mappers.MedicalServiceMapper;
import com.example.javaclasses.Repositories.MaterialRepository;
import com.example.javaclasses.Repositories.MedicalServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;


@Service //@RequiredArgsConstructor
public class MedicalServiceService {
    private final MedicalServiceRepository medicalServiceRepository;

    private final MaterialRepository materialRepository;
    private final MedicalServiceMapper medicalServiceMapper;

    private final DoctorMapper doctorMapper;
    private final DoctorService doctorService;

    private final MaterialMapper materialMapper;

    @Autowired
    public MedicalServiceService(@Lazy final DoctorMapper doctorMapper,
                                 final MaterialMapper materialMapper,
                                 final MedicalServiceRepository medicalServiceRepository,
                                 final MedicalServiceMapper medicalServiceMapper,
                                 final MaterialRepository materialRepository,
                                 @Lazy final DoctorService doctorService) {
        this.doctorMapper = doctorMapper;
        this.medicalServiceRepository = medicalServiceRepository;
        this.medicalServiceMapper = medicalServiceMapper;
        this.doctorService = doctorService;
        this.materialMapper = materialMapper;
        this.materialRepository = materialRepository;
    }

    public List<MedicalServiceDTO> getAllMedicalServices() {
        List<MedicalService> medicalServices = medicalServiceRepository.findAll();
        List<MedicalServiceDTO> medicalServiceDTOs = new ArrayList<>();
        for (MedicalService medicalService : medicalServices) {
            medicalServiceDTOs.add(medicalServiceMapper.toMedicalServiceDTO(medicalService));
        }
        return medicalServiceDTOs;
    }

    public MedicalServiceDTO getMedicalServiceById(Long id) {
        MedicalService medicalService = medicalServiceRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("MedicalService id: " + id));
        return medicalServiceMapper.toMedicalServiceDTO(medicalService);
    }

    public List<MaterialDTO> getMaterialsByMedicalService(Long id) {
        MedicalService medicalService = medicalServiceRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("MedicalService id: " + id));


        List<Material> materials = medicalService.getMaterialList();
        List<MaterialDTO> materialsDTO = new ArrayList<>();
        for (Material material : materials) {
            materialsDTO.add(materialMapper.toMaterialDTO(material));
        }
        return materialsDTO;
    }

    public Long getFullPrice(Long idService, Long idMaterial) {
        MedicalService medicalService = medicalServiceRepository.findById(idService).orElseThrow(() -> new ElementNotFoundException("MedicalService id: " + idService));

        if(idMaterial == 0){
            return medicalService.getBaseprice();
        }
        else {
        Material material = materialRepository.findById(idMaterial).orElseThrow(() -> new ElementNotFoundException("Material id: " + idMaterial));

        MaterialDTO materialDTO = materialMapper.toMaterialDTO(material);;

        return materialDTO.getPrice() + medicalService.getBaseprice();}
    }

    public List<MedicalServiceDTO> getMedicalServicesByType(Integer type) {

        List<MedicalService> medicalServices = medicalServiceRepository.findAll();
        List<MedicalServiceDTO> medicalServicesDTO = new ArrayList<>();
        for (MedicalService medicalService : medicalServices) {

            if(medicalService.getType() == type) {
                medicalServicesDTO.add(medicalServiceMapper.toMedicalServiceDTO(medicalService));
            }
        }
        return medicalServicesDTO;
    }

    public List<DoctorDTO> getAllServiceDoctors(Long id) {
        MedicalService medicalService = medicalServiceRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("MedicalService id: " + id));
        List<DoctorDTO> doctorsDTO = doctorService.getDoctorsByType(medicalService.getType());

        return doctorsDTO;
    }

    public MedicalServiceDTO makeMedicalService(MedicalServiceDTO medicalServiceDTO) {
        MedicalService medicalService = medicalServiceMapper.toMedicalService(medicalServiceDTO);

        MedicalService createdMedicalService = medicalServiceRepository.save(medicalService);
        return medicalServiceMapper.toMedicalServiceDTO(createdMedicalService);
    }

    public MedicalServiceDTO updateMedicalService(Long id, MedicalServiceDTO updatedMedicalServiceDTO) {
        MedicalService medicalService = medicalServiceRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("MedicalService id: " + id));
        MedicalService updatedMedicalService = medicalServiceMapper.toMedicalService(updatedMedicalServiceDTO);

        updatedMedicalService.setId(id);
        updatedMedicalService.setMaterialList(medicalService.getMaterialList());
        medicalServiceRepository.save(updatedMedicalService);

        return updatedMedicalServiceDTO;
    }

    public void deleteMedicalService(Long id) {
        medicalServiceRepository.deleteById(id);
    }
}
