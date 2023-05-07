package com.example.javaclasses.Services;

import com.example.javaclasses.DTOs.DoctorDTO;
import com.example.javaclasses.DTOs.MedicalServiceDTO;
import com.example.javaclasses.Entities.Doctor;
import com.example.javaclasses.Errors.ElementNotFoundException;
import com.example.javaclasses.Mappers.DoctorMapper;
import com.example.javaclasses.Mappers.MedicalServiceMapper;
import com.example.javaclasses.Repositories.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DoctorService {
    @Autowired
    private final DoctorRepository doctorRepository;
    @Autowired
    private final DoctorMapper doctorMapper;
    @Autowired
    private final MedicalServiceMapper medicalServiceMapper;
    @Autowired
    private final MedicalServiceService medicalServiceService;
    public List<DoctorDTO> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOs = new ArrayList<>();
        for (Doctor doctor : doctors) {
            doctorDTOs.add(doctorMapper.toDoctorDTO(doctor));
        }
        return doctorDTOs;
    }


    public DoctorDTO getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Doctor id: " + id));
        return doctorMapper.toDoctorDTO(doctor);
    }

    public List<DoctorDTO> getDoctorsByType(Integer type) {

        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorsDTO = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if(doctor.getType() == type) {
                System.out.println(" Service doctor toDTO");
                doctorsDTO.add(doctorMapper.toDoctorDTO(doctor));
            }
        }
        return doctorsDTO;
    }

    public List<MedicalServiceDTO> getAllDoctorServices(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Doctor id: " + id));

        List<MedicalServiceDTO> medicalServicesDTO = medicalServiceService.getMedicalServicesByType(doctor.getType());

        return medicalServicesDTO;
    }

    public DoctorDTO makeDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.toDoctor(doctorDTO);


        Doctor createdDoctor = doctorRepository.save(doctor);



        return doctorMapper.toDoctorDTO(createdDoctor);
    }

    public DoctorDTO updateDoctor(Long id, DoctorDTO updatedDoctorDTO) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("Doctor id: " + id));
        Doctor updatedDoctor = doctorMapper.toDoctor(updatedDoctorDTO);
        updatedDoctor.setId(id);

        doctorRepository.save(updatedDoctor);
        return updatedDoctorDTO;
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

}
