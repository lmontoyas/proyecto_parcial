package com.example.demo.cita.model;

import com.example.demo.actividad.model.Actividad;
import com.example.demo.consultorio.model.Consultorio;
import com.example.demo.diagnostico.model.Diagnostico;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.cglib.core.Local;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long episodio;

    @NonNull
    private Long historia;

    private LocalDateTime fechaPedido = LocalDateTime.now();

    @NonNull
    private LocalDateTime fechaCita;

    @ManyToOne
    @JoinColumn(name = "actividad", nullable = false)
    private Actividad actividad;

    @ManyToOne
    @JoinColumn(name = "consultorio", nullable = false)
    private Consultorio consultorio;

    @ManyToOne
    @JoinColumn(name = "diagnostico", nullable = true)
    private Diagnostico diagnostico;

    private String estado = "Programado";
}

