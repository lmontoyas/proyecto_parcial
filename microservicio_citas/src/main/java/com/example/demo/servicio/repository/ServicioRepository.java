package com.example.demo.servicio.repository;

import com.example.demo.servicio.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, String> {
}
