package com.example.demo.cita.controller;

import com.example.demo.cita.model.Cita;
import com.example.demo.cita.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cita")
public class CitaController {
    @Autowired
    private CitaService citaService;

    // Obtener todas las citas
    @GetMapping
    public List<Cita> mostrarCitas(){
        return citaService.getAllCitas();
    }

    // Obtener una cita por episodio
    @GetMapping("/{episodio}")
    public ResponseEntity<Map<String, Object>> mostrarCita(@PathVariable Long episodio){
        return citaService.getCitaById(episodio);
    }

    @GetMapping("/agenda/{historia}")
    public ResponseEntity<Map<String, Object>> mostrarCitasPorHistoria(@PathVariable Long historia){
        return citaService.mostrarCitasPorHistoria(historia);
    }

    //Crear una cita
    @PostMapping
    public ResponseEntity<Map<String, Object>> crearCita(@RequestBody Cita cita){
        return citaService.saveCita(cita);
    }

    //Actualizar una cita
    @PutMapping("/{episodio}")
    public ResponseEntity<Map<String, Object>> actualizarCita(@PathVariable Long episodio, @RequestBody Cita citaDetalle){
        return citaService.updateCita(episodio, citaDetalle);
    }

    // Eliminar una cita
    @DeleteMapping("/{episodio}")
    public ResponseEntity<Map<String, Object>> deleteCita(@PathVariable Long episodio) {
        return citaService.deleteCita(episodio);
    }
}
