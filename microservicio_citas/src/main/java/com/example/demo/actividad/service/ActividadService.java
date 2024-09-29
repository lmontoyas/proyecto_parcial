package com.example.demo.actividad.service;

import com.example.demo.actividad.model.Actividad;
import com.example.demo.actividad.repository.ActividadRepository;
import com.example.demo.diagnostico.model.Diagnostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ActividadService {
    @Autowired
    private ActividadRepository actividadRepository;

    public ResponseEntity<Map<String, Object>> buildActividadResponse(Actividad actividad, String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("actividad", actividad);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

    public List<Actividad> getAllActividades() {
        return actividadRepository.findAll();
    }

    public ResponseEntity<Map<String, Object>> getActividadById(String codigo) {
        Optional<Actividad> actividad = actividadRepository.findById(codigo);

        if (actividad.isPresent()) {
            return buildActividadResponse(actividad.get(),"Actividad encontrada exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Actividad con código " + codigo + " no encontrada", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> saveActividad(Actividad actividad) {
        Optional<Actividad> optionalActividad = actividadRepository.findById(actividad.getCodigo());

        if(optionalActividad.isEmpty()) {
            Actividad savedActividad = actividadRepository.save(actividad);
            return buildActividadResponse(savedActividad,"Actividad creada exitosamente", HttpStatus.CREATED);
        }
        return buildErrorResponse("Actividad con código " + actividad.getCodigo() + " no encontrada", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> updateActividad(String codigo, Actividad actividadDetails) {
        Optional<Actividad> optionalActividad = actividadRepository.findById(codigo);
        if (optionalActividad.isPresent()) {
            Actividad actividad = optionalActividad.get();
            actividad.setDescripcion(actividadDetails.getDescripcion());
            actividad.setServicio(actividadDetails.getServicio());
            actividadRepository.save(actividad);
            return buildActividadResponse(actividad,"Actividad modificada exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Actividad con código " + codigo + " no encontrada", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> deleteActividad(String codigo) {
        Optional<Actividad> optionalActividad = actividadRepository.findById(codigo);
        if (optionalActividad.isPresent()) {
            actividadRepository.delete(optionalActividad.get());
            return buildActividadResponse(optionalActividad.get(),"Actividad eliminada exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Actividad con código " + codigo + " no encontrada", HttpStatus.NOT_FOUND);
    }
}
