package com.example.demo.diagnostico.repository;

import com.example.demo.diagnostico.model.Diagnostico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, String> {
}
