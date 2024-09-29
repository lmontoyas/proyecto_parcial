package com.example.demo.actividad.controller;

import com.example.demo.actividad.model.Actividad;
import com.example.demo.actividad.service.ActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/actividad")
public class ActividadController {
    @Autowired
    private ActividadService actividadService;

    @GetMapping
    public List<Actividad> getAllActividades() {
        return actividadService.getAllActividades();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> getActividadById(@PathVariable String codigo) {
        return actividadService.getActividadById(codigo);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createActividad(@RequestBody Actividad actividad) {
        return actividadService.saveActividad(actividad);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> updateActividad(@PathVariable String codigo, @RequestBody Actividad actividadDetails) {
        return actividadService.updateActividad(codigo, actividadDetails);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> deleteActividad(@PathVariable String codigo) {
        return actividadService.deleteActividad(codigo);
    }

}
