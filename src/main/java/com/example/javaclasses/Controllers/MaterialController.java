package com.example.javaclasses.Controllers;

import com.example.javaclasses.DTOs.MaterialDTO;
import com.example.javaclasses.Entities.Views;
import com.example.javaclasses.Services.MaterialService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;

    @JsonView(Views.Public.class)
    @GetMapping("/materials")
    public ResponseEntity<List<MaterialDTO>> getAllMaterials() {
        return ResponseEntity.ok().body(materialService.getAllMaterials());
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/materials/all")
    public ResponseEntity<List<MaterialDTO>> getAllMaterialsWithId() {
        return ResponseEntity.ok().body(materialService.getAllMaterials());
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/materials/{id}")
    public ResponseEntity<MaterialDTO> getMaterialById(@PathVariable Long id) {
        return ResponseEntity.ok().body(materialService.getMaterialById(id));
    }

    @PostMapping("/materials")
    public ResponseEntity<MaterialDTO> createMaterial(@RequestBody MaterialDTO materialDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.makeMaterial(materialDTO));
    }

    @PutMapping("/materials/{id}")
    public ResponseEntity<MaterialDTO> updateMaterial(@PathVariable Long id, @RequestBody MaterialDTO materialDTO) {
        return ResponseEntity.ok().body(materialService.updateMaterial(id, materialDTO));
    }

    @DeleteMapping("/materials/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }
}
