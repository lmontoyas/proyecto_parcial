package com.example.demo.actividad.repository;

import com.example.demo.actividad.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadRepository extends JpaRepository<Actividad, String> {
}
