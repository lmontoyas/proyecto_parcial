package com.example.demo.consultorio.repository;

import com.example.demo.consultorio.model.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
    Optional<Consultorio> findByDescripcion(String descripcion);
}
