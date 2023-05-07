package com.example.javaclasses.Mappers;

import com.example.javaclasses.DTOs.MaterialDTO;
import com.example.javaclasses.Entities.Material;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {
    public Material toMaterial(MaterialDTO materialDTO) {
        Material material = new Material();
        material.setName(materialDTO.getName());
        material.setPrice(materialDTO.getPrice());
        material.setService_id(materialDTO.getService_id());
        return material;
    }

    public MaterialDTO toMaterialDTO(Material material) {
        MaterialDTO materialDTO = new MaterialDTO();
        materialDTO.setName(material.getName());
        materialDTO.setPrice(material.getPrice());
        materialDTO.setService_id(material.getService_id());
        materialDTO.setId(material.getId());
        return materialDTO;
    }
}
