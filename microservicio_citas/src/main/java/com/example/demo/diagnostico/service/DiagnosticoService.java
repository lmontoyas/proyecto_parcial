package com.example.demo.diagnostico.service;

import com.example.demo.diagnostico.model.Diagnostico;
import com.example.demo.diagnostico.repository.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DiagnosticoService {
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    public ResponseEntity<Map<String, Object>> buildDiagnosticoResponse(Diagnostico diagnostico, String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("diagnostico", diagnostico);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

    public List<Diagnostico> getAllDiagnosticos() {
        return diagnosticoRepository.findAll();
    }

    public ResponseEntity<Map<String, Object>> getDiagnosticoById(String codigo) {
        Optional<Diagnostico> diagnostico = diagnosticoRepository.findById(codigo);

        // Si se encuentra el diagnóstico, lo devuelve con estado 200 OK
        if (diagnostico.isPresent()) {
            return buildDiagnosticoResponse(diagnostico.get(),"Diagnóstico encontrado exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Diagnóstico con código " + codigo + " no encontrado", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> saveDiagnostico(Diagnostico diagnostico) {
        // Guardar el diagnóstico y obtener el objeto guardado
        Optional<Diagnostico> optionalDiagnostico = diagnosticoRepository.findById(diagnostico.getCodigo());

        if(optionalDiagnostico.isEmpty()) {
            Diagnostico savedDiagnostico = diagnosticoRepository.save(diagnostico);
            return buildDiagnosticoResponse(savedDiagnostico,"Diagnóstico creado exitosamente", HttpStatus.CREATED);
        }
        return buildErrorResponse("Diagnóstico con código " + diagnostico.getCodigo() + " no encontrado", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> updateDiagnostico(String codigo, Diagnostico diagnosticoDetails) {
        Optional<Diagnostico> optionalDiagnostico = diagnosticoRepository.findById(codigo);
        if (optionalDiagnostico.isPresent()) {
            Diagnostico diagnostico = optionalDiagnostico.get();
            diagnostico.setDescripcion(diagnosticoDetails.getDescripcion());
            diagnosticoRepository.save(diagnostico);
            return buildDiagnosticoResponse(diagnostico,"Diagnóstico modificado exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Diagnóstico con código " + codigo + " no encontrado", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> deleteDiagnostico(String codigo) {
        Optional<Diagnostico> optionalDiagnostico = diagnosticoRepository.findById(codigo);
        if (optionalDiagnostico.isPresent()) {
            diagnosticoRepository.delete(optionalDiagnostico.get());
            return buildDiagnosticoResponse(optionalDiagnostico.get(),"Diagnóstico eliminado exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Diagnóstico con código " + codigo + " no encontrado", HttpStatus.NOT_FOUND);
    }
}
