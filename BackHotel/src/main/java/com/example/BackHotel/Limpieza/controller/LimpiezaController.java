package com.example.BackHotel.Limpieza.controller;

import com.example.BackHotel.Limpieza.model.Limpieza;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/limpiezas")
@CrossOrigin
public class LimpiezaController {

    private final LimpiezaService limpiezaService;

    public LimpiezaController(LimpiezaService limpiezaService) {
        this.limpiezaService = limpiezaService;
    }

    public static class LimpiezaRequest {
        public Long habitacionId;
        public Long camareraId;
    }

    @GetMapping("/historial")
    public List<Limpieza> historial(@RequestParam(defaultValue = "50") int limit) {
        return limpiezaService.listarUltimos(limit);
    }

    @PostMapping("/marcar-limpia")
    public ResponseEntity<Limpieza> marcarLimpia(@RequestBody LimpiezaRequest req) {
        return limpiezaService.marcarLimpia(req.habitacionId, req.camareraId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/habitacion/{habitacionId}")
    public List<Limpieza> historialPorHabitacion(@PathVariable Long habitacionId) {
        return limpiezaService.historialPorHabitacion(habitacionId);
    }

    @GetMapping("/camarera/{camareraId}")
    public List<Limpieza> historialPorCamarera(@PathVariable Long camareraId) {
        return limpiezaService.historialPorCamarera(camareraId);
    }
}
