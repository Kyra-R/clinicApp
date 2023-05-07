package com.example.javaclasses.Repositories;
import com.example.javaclasses.Entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
