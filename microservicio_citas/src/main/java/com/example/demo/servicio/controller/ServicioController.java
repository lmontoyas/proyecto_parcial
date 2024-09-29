package com.example.demo.servicio.controller;

import com.example.demo.servicio.model.Servicio;
import com.example.demo.servicio.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/servicio")
public class ServicioController {
    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> getAllServicios() {
        return servicioService.getAllServicios();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> getServicioById(@PathVariable String codigo) {
        return servicioService.getServicioById(codigo);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createServicio(@RequestBody Servicio servicio) {
        return servicioService.saveServicio(servicio);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> updateServicio(@PathVariable String codigo, @RequestBody Servicio servicioDetails) {
        return servicioService.updateServicio(codigo, servicioDetails);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Map<String, Object>> deleteServicio(@PathVariable String codigo) {
        return servicioService.deleteServicio(codigo);
    }
}
