package com.example.javaclasses.Controllers;

import com.example.javaclasses.DTOs.DoctorDTO;
import com.example.javaclasses.DTOs.MaterialDTO;
import com.example.javaclasses.DTOs.MedicalServiceDTO;
import com.example.javaclasses.Entities.Views;
import com.example.javaclasses.Services.MedicalServiceService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MedicalServiceController {
    private final MedicalServiceService medicalServiceService;

    @JsonView(Views.Public.class)
    @GetMapping("/medservices")
    public ResponseEntity<List<MedicalServiceDTO>> getAllMedicalServices() {
        return ResponseEntity.ok().body(medicalServiceService.getAllMedicalServices());
    }
    @JsonView(Views.Internal.class)
    @GetMapping("/medservices/all")
    public ResponseEntity<List<MedicalServiceDTO>> getAllMedicalServicesWithId() {
        return ResponseEntity.ok().body(medicalServiceService.getAllMedicalServices());
    }
    @JsonView(Views.Internal.class)
    @GetMapping("/medservices/{id}")
    public ResponseEntity<MedicalServiceDTO> getMedicalServiceById(@PathVariable Long id) {
        return ResponseEntity.ok().body(medicalServiceService.getMedicalServiceById(id));
    }

    @GetMapping("/medservices/{id_service}/materials/{id_material}")
    public ResponseEntity<Long> getFullPrice(@PathVariable Long id_service, @PathVariable Long id_material) {
        return ResponseEntity.ok().body(medicalServiceService.getFullPrice(id_service, id_material));
    }

    @JsonView(Views.Public.class)
    @GetMapping("/medservices/type/{type}")
    public ResponseEntity<List<MedicalServiceDTO>> getMedicalServicesByType(@PathVariable Integer type) {
        return ResponseEntity.ok().body(medicalServiceService.getMedicalServicesByType(type));
    }
    @JsonView(Views.Public.class)
    @GetMapping("/medservices/{id}/doctors")
    public ResponseEntity<List<DoctorDTO>> getAllServiceDoctors(@PathVariable Long id) {
        return ResponseEntity.ok().body(medicalServiceService.getAllServiceDoctors(id));
    }
    @JsonView(Views.Public.class)
    @GetMapping("/medservices/{id}/materials")
    public ResponseEntity<List<MaterialDTO>> getAllServiceMaterials(@PathVariable Long id) {
        return ResponseEntity.ok().body(medicalServiceService.getMaterialsByMedicalService(id));
    }

    @PostMapping("/medservices")
    public ResponseEntity<MedicalServiceDTO> createMedicalService(@RequestBody MedicalServiceDTO medicalServiceDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalServiceService.makeMedicalService(medicalServiceDTO));
    }

    @PutMapping("/medservices/{id}")
    public ResponseEntity<MedicalServiceDTO> updateMedicalService(@PathVariable Long id, @RequestBody MedicalServiceDTO medicalServiceDTO) {
        return ResponseEntity.ok().body(medicalServiceService.updateMedicalService(id, medicalServiceDTO));
    }

    @DeleteMapping("/medservices/{id}")
    public ResponseEntity<Void> deleteMedicalService(@PathVariable Long id) {
        medicalServiceService.deleteMedicalService(id);
        return ResponseEntity.noContent().build();
    }
}
