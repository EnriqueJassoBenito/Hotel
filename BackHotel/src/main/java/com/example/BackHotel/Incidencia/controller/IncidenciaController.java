package com.example.BackHotel.Incidencia.controller;

import com.example.BackHotel.Habitacion.model.EstadoHabitacion;
import com.example.BackHotel.Incidencia.model.Incidencia;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
@CrossOrigin
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    public static class CrearIncidenciaRequest {
        public Long habitacionId;
        public Long camareraId;
        public String descripcion;
        public List<String> fotos;
    }

    @PostMapping
    public ResponseEntity<Incidencia> crear(@RequestBody CrearIncidenciaRequest req) {
        return incidenciaService.crear(
                        req.habitacionId,
                        req.camareraId,
                        req.descripcion,
                        req.fotos
                )
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public List<Incidencia> listarTodas() {
        return incidenciaService.listarTodas();
    }

    @GetMapping("/abiertas")
    public List<Incidencia> listarAbiertas() {
        return incidenciaService.listarAbiertas();
    }

    public static class CerrarIncidenciaRequest {
        public Long recepcionId;
        public EstadoHabitacion nuevoEstado;
    }

    @PutMapping("/{id}/cerrar")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Incidencia> cerrar(
            @PathVariable Long id,
            @RequestBody CerrarIncidenciaRequest req) {

        return incidenciaService.cerrar(id, req.recepcionId, req.nuevoEstado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
