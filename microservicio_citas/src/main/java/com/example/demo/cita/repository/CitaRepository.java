package com.example.demo.cita.repository;

import com.example.demo.cita.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByHistoria(Long historia);

    // Consulta para buscar citas en el mismo consultorio con fecha cercana (30 minutos antes o después)
    @Query(value = "SELECT * FROM Cita c WHERE c.consultorio = :consultorioId " +
            "AND ABS(EXTRACT(EPOCH FROM (c.fecha_cita - :fechaCita)) / 60) < 30",
            nativeQuery = true)
    List<Cita> findCitasInSameConsultorioWithin30Minutes(@Param("consultorioId") Long consultorioId,
                                                         @Param("fechaCita") Timestamp fechaCita);
}
