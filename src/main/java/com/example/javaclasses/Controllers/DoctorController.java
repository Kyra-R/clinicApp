package com.example.javaclasses.Controllers;

import com.example.javaclasses.DTOs.DoctorDTO;
import com.example.javaclasses.DTOs.MedicalServiceDTO;

import com.example.javaclasses.Entities.Views;
import com.example.javaclasses.Services.DoctorService;
import com.example.javaclasses.Services.MaterialService;
import com.example.javaclasses.Services.MedicalServiceService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@JsonView
public class DoctorController {
    private final DoctorService doctorService;
    private final MedicalServiceService medicalServiceService;
    private final MaterialService materialService;
    @JsonView(Views.Public.class)
    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        return ResponseEntity.ok().body(doctorService.getAllDoctors());
    }

    @JsonView(Views.Internal.class)
    @GetMapping("/doctors/all")
    public ResponseEntity<List<DoctorDTO>> getAllDoctorsWithId() {
        return ResponseEntity.ok().body(doctorService.getAllDoctors());
    }


    @GetMapping({"/all"})
    public ModelAndView getAll() {
        ModelAndView model = new ModelAndView("doctors");
        model.addObject("doctors_all",doctorService.getAllDoctors());
        model.addObject("medservices_all",medicalServiceService.getAllMedicalServices());
        model.addObject("materials_all",medicalServiceService);
        Long price = 0L;
       model.addObject("material_price",price);
        return model;
    }

    @GetMapping("/doctors/{id}/test")
    public String getDoctorById(@PathVariable Long id, Model model) {
        return doctorService.getDoctorById(id).getName();
    }


    @JsonView(Views.Internal.class)
    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok().body(doctorService.getDoctorById(id));
    }
    @JsonView(Views.Public.class)
    @GetMapping("/doctors/type/{type}")
    public ResponseEntity<List<DoctorDTO>> getDoctorsByType(@PathVariable Integer type) {
        return ResponseEntity.ok().body(doctorService.getDoctorsByType(type));
    }

    @GetMapping("/doctors/{id}/services")
    public ResponseEntity<List<MedicalServiceDTO>> getAllDoctorServices(@PathVariable Long id) {

        return ResponseEntity.ok().body(doctorService.getAllDoctorServices(id));
    }

    @PostMapping("/doctors")
    public ResponseEntity<DoctorDTO> makeDoctor(@RequestBody DoctorDTO doctorDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.makeDoctor(doctorDTO));
    }


    @PutMapping("/doctors/{id}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable Long id, @RequestBody DoctorDTO doctorDTO) {
        return ResponseEntity.ok().body(doctorService.updateDoctor(id, doctorDTO));
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
