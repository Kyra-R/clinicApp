package com.example.javaclasses.Repositories;

import com.example.javaclasses.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
