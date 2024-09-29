package com.example.demo.diagnostico.controller;

import com.example.demo.diagnostico.model.Diagnostico;
import com.example.demo.diagnostico.repository.DiagnosticoRepository;
import com.example.demo.diagnostico.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/diagnostico")
public class DiagnosticoController {
    @Autowired
    private DiagnosticoService diagnosticoService;

    @GetMapping()
    public List<Diagnostico> getAllDiagnosticos() {
        return diagnosticoService.getAllDiagnosticos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> getDiagnosticoById(@PathVariable String codigo) {
        return diagnosticoService.getDiagnosticoById(codigo);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createDiagnostico(@RequestBody Diagnostico diagnostico) {
        return diagnosticoService.saveDiagnostico(diagnostico);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> updateDiagnostico(@PathVariable String codigo, @RequestBody Diagnostico diagnosticoDetails) {
        return diagnosticoService.updateDiagnostico(codigo, diagnosticoDetails);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> deleteDiagnostico(@PathVariable String codigo) {
        return diagnosticoService.deleteDiagnostico(codigo);
    }
}
