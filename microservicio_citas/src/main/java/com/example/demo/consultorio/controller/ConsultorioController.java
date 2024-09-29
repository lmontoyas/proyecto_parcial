package com.example.demo.consultorio.controller;

import com.example.demo.consultorio.model.Consultorio;
import com.example.demo.consultorio.service.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/consultorio")
public class ConsultorioController {
    @Autowired
    private ConsultorioService consultorioService;

    @GetMapping
    public List<Consultorio> getAllConsultorios() {
        return consultorioService.getAllConsultorios();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> getConsultorioById(@PathVariable Long codigo) {
        return consultorioService.getConsultorioById(codigo);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createConsultorio(@RequestBody Consultorio consultorio) {
        return consultorioService.saveConsultorio(consultorio);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> updateConsultorio(@PathVariable Long codigo, @RequestBody Consultorio consultorioDetails) {
        return consultorioService.updateConsultorio(codigo, consultorioDetails);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> deleteConsultorio(@PathVariable Long codigo) {
        return consultorioService.deleteConsultorio(codigo);
    }
}
