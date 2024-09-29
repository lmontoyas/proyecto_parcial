package com.example.demo.actividad.model;

import com.example.demo.servicio.model.Servicio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Actividad {
    @Id
    private String codigo;

    @NonNull
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "servicio", nullable = false)
    private Servicio servicio;
}
