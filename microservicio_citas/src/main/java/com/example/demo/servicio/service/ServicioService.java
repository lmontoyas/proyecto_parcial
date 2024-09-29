package com.example.demo.servicio.service;

import com.example.demo.diagnostico.model.Diagnostico;
import com.example.demo.servicio.model.Servicio;
import com.example.demo.servicio.repository.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServicioService {
    @Autowired
    private ServicioRepository servicioRepository;

    public ResponseEntity<Map<String, Object>> buildServicioResponse(Servicio servicio, String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("diagnostico", servicio);
        return ResponseEntity.status(status).body(response);
    }

    public ResponseEntity<Map<String, Object>> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.status(status).body(response);
    }

    public List<Servicio> getAllServicios() {
        return servicioRepository.findAll();
    }

    public ResponseEntity<Map<String, Object>> getServicioById(String codigo) {
        Optional<Servicio> servicio = servicioRepository.findById(codigo);

        // Si se encuentra el diagnóstico, lo devuelve con estado 200 OK
        if (servicio.isPresent()) {
            return buildServicioResponse(servicio.get(),"Servicio encontrado exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Servicio con código " + codigo + " no encontrado", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> saveServicio(Servicio servicio) {
        Optional<Servicio> optionalServicio = servicioRepository.findById(servicio.getCodigo());

        if(optionalServicio.isEmpty()) {
            Servicio savedServicio = servicioRepository.save(servicio);
            return buildServicioResponse(savedServicio,"Servicio creado exitosamente", HttpStatus.CREATED);
        }
        return buildErrorResponse("Servicio con código " + servicio.getCodigo() + " no encontrado", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> updateServicio(String codigo, Servicio servicioDetails) {
        Optional<Servicio> optionalServicio = servicioRepository.findById(codigo);
        if (optionalServicio.isPresent()) {
            Servicio servicio = optionalServicio.get();
            servicio.setDescripcion(servicioDetails.getDescripcion());
            servicioRepository.save(servicio);
            return buildServicioResponse(servicio,"Servicio modificado exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Servicio con código " + codigo + " no encontrado", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Map<String, Object>> deleteServicio(String codigo) {
        Optional<Servicio> optionalServicio = servicioRepository.findById(codigo);
        if (optionalServicio.isPresent()) {
            servicioRepository.delete(optionalServicio.get());
            return buildServicioResponse(optionalServicio.get(),"Servicio eliminado exitosamente", HttpStatus.OK);
        }
        return buildErrorResponse("Servicio con código " + codigo + " no encontrado", HttpStatus.NOT_FOUND);
    }
}
