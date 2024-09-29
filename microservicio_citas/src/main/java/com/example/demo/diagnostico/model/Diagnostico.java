package com.example.demo.diagnostico.model;

import com.example.demo.cita.model.Cita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diagnostico {
    @Id
    private String codigo;

    private String descripcion;
}
