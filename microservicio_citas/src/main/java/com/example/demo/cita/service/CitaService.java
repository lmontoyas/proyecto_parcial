package com.example.demo.cita.service;

import com.example.demo.actividad.model.Actividad;
import com.example.demo.cita.model.Cita;
import com.example.demo.cita.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CitaService {
    @Autowired
    private CitaRepository citaRepository;

    //Obtener todas las citas
    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    public ResponseEntity<Map<String, Object>> buildCitasResponse(List<Cita> citas, String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("citas", citas);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<Map<String, Object>> buildCitaResponse(Cita cita, String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("cita", cita);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

    // Obtener una cita por ID
    public ResponseEntity<Map<String, Object>> getCitaById(Long episodio) {
        Optional<Cita> cita = citaRepository.findById(episodio);

        if (cita.isPresent()) {
            return buildCitaResponse(cita.get(),"Cita encontrada exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Cita con número de episodio " + episodio + " no es encontrada", HttpStatus.NOT_FOUND);
    }

    // Obtener una cita por historia
    public ResponseEntity<Map<String, Object>> mostrarCitasPorHistoria(Long historia) {
        List<Cita> citas = citaRepository.findByHistoria(historia);

        if (!citas.isEmpty()) {
            return buildCitasResponse(citas,"Citas encontradas exitosamente", HttpStatus.OK);
        } else {
            return buildErrorResponse("No se encontraron citas para la historia médica " + historia, HttpStatus.NOT_FOUND);
        }
    }

    // Guardar una nueva cita
    public ResponseEntity<Map<String, Object>> saveCita(Cita cita) {
        // Validar si ya existe una cita en el mismo consultorio en un rango de 30 minutos
        List<Cita> citasConflictivas = citaRepository.findCitasInSameConsultorioWithin30Minutes(
                cita.getConsultorio().getCodigo(), Timestamp.valueOf(cita.getFechaCita())
        );

        if (!citasConflictivas.isEmpty()) {
            return buildErrorResponse("Ya existe una cita programada en el mismo consultorio en los próximos 30 minutos", HttpStatus.CONFLICT);
        }

        // Si no hay conflicto, guarda la cita
        citaRepository.save(cita);
        return buildCitaResponse(cita,"Citas creada exitosamente", HttpStatus.CREATED);
    }

    // Actualizar una cita existente
    public ResponseEntity<Map<String, Object>> updateCita(Long episodio, Cita citaDetails) {
        Optional<Cita> optionalCita = citaRepository.findById(episodio);
        if (optionalCita.isPresent()) {
            Cita cita = optionalCita.get();
            cita.setDiagnostico(citaDetails.getDiagnostico());
            cita.setEstado(citaDetails.getEstado());
            citaRepository.save(cita);
            return buildCitaResponse(cita,"Cita modificada exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Cita con episodio " + episodio + " no es encontrada", HttpStatus.NOT_FOUND);
    }

    // Eliminar una cita por ID
    public ResponseEntity<Map<String, Object>> deleteCita(Long episodio) {
        Optional<Cita> optionalCita = citaRepository.findById(episodio);
        if (optionalCita.isPresent()) {
            citaRepository.delete(optionalCita.get());
            return buildCitaResponse(optionalCita.get(),"Actividad eliminada exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Cita con episodio " + episodio + " no es encontrada", HttpStatus.NOT_FOUND);
    }
}
