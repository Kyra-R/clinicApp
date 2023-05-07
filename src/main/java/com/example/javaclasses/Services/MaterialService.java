package com.example.javaclasses.Services;

import com.example.javaclasses.DTOs.MaterialDTO;
import com.example.javaclasses.Entities.Material;
import com.example.javaclasses.Errors.ElementNotFoundException;
import com.example.javaclasses.Mappers.MaterialMapper;
import com.example.javaclasses.Repositories.MaterialRepository;
import com.example.javaclasses.Repositories.MedicalServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;
    private final MedicalServiceRepository medicalServiceRepository;

    public List<MaterialDTO> getAllMaterials() {
        List<Material> categories = materialRepository.findAll();
        List<MaterialDTO> materialDTOs = new ArrayList<>();
        for (Material material : categories) {
            materialDTOs.add(materialMapper.toMaterialDTO(material));
        }
        return materialDTOs;
    }

    public MaterialDTO getMaterialById(Long id) {
        Material material = materialRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Material id: " + id));
        return materialMapper.toMaterialDTO(material);
    }

    public MaterialDTO makeMaterial(MaterialDTO materialDTO) {
        medicalServiceRepository.findById(materialDTO.getService_id()).orElseThrow(() ->
                new ElementNotFoundException("MedicalService id: " + materialDTO.getService_id()));
        Material material = materialMapper.toMaterial(materialDTO);
        Material createdMaterial = materialRepository.save(material);
        return materialMapper.toMaterialDTO(createdMaterial);
    }

    public MaterialDTO updateMaterial(Long id, MaterialDTO updatedMaterialDTO) {
        Material material = materialRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Material id: " + id));
        Material updatedMaterial = materialMapper.toMaterial(updatedMaterialDTO);

        updatedMaterial.setId(id);
        materialRepository.save(updatedMaterial);
        return updatedMaterialDTO;
    }

    public void deleteMaterial(Long id) {
        materialRepository.deleteById(id);
    }

}
