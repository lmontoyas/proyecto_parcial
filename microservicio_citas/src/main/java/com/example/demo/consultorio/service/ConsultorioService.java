package com.example.demo.consultorio.service;

import com.example.demo.actividad.model.Actividad;
import com.example.demo.consultorio.model.Consultorio;
import com.example.demo.consultorio.repository.ConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ConsultorioService {
    @Autowired
    private ConsultorioRepository consultorioRepository;

    public ResponseEntity<Map<String, Object>> buildConsultorioResponse(Consultorio consultorio, String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("consultorio", consultorio);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

    public List<Consultorio> getAllConsultorios() {
        return consultorioRepository.findAll();
    }

    public ResponseEntity<Map<String, Object>> getConsultorioById(Long codigo) {
        Optional<Consultorio> consultorio = consultorioRepository.findById(codigo);

        if (consultorio.isPresent()) {
            return buildConsultorioResponse(consultorio.get(),"Consultorio encontrado exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Consultorio con código " + codigo + " no encontrado", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> saveConsultorio(Consultorio consultorio) {
        Optional<Consultorio> optionalConsultorio = consultorioRepository.findByDescripcion(consultorio.getDescripcion());

        if(optionalConsultorio.isEmpty()) {
            Consultorio savedConsultorio = consultorioRepository.save(consultorio);
            return buildConsultorioResponse(savedConsultorio,"Cnsultorio creado exitosamente", HttpStatus.CREATED);
        }
        return buildErrorResponse("Consultorio con código " + consultorio.getCodigo() + " no encontrado", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> updateConsultorio(Long codigo, Consultorio consultorioDetails) {
        Optional<Consultorio> optionalConsultorio = consultorioRepository.findById(codigo);
        if (optionalConsultorio.isPresent()) {
            Consultorio consultorio = optionalConsultorio.get();
            consultorio.setDescripcion(consultorioDetails.getDescripcion());
            consultorioRepository.save(consultorio);
            return buildConsultorioResponse(consultorio,"Consultorio modificado exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Consultorio con código " + codigo + " no encontrado", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> deleteConsultorio(Long codigo) {
        Optional<Consultorio> optionalConsultorio = consultorioRepository.findById(codigo);
        if (optionalConsultorio.isPresent()) {
            consultorioRepository.delete(optionalConsultorio.get());
            return buildConsultorioResponse(optionalConsultorio.get(),"Consultorio eliminad exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Consultorio con código " + codigo + " no encontrado", HttpStatus.NOT_FOUND);
    }
}