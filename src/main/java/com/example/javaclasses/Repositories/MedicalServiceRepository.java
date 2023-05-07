package com.example.javaclasses.Repositories;
import com.example.javaclasses.Entities.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {
}
