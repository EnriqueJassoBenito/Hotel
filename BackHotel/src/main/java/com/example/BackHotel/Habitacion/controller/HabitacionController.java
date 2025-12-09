package com.example.BackHotel.Habitacion.controller;

import com.example.BackHotel.Habitacion.model.EstadoHabitacion;
import com.example.BackHotel.Habitacion.model.Habitacion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")
@CrossOrigin
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping
    public List<Habitacion> listar() {
        return habitacionService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> buscarPorId(@PathVariable Long id) {
        return habitacionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Habitacion crear(@RequestBody Habitacion habitacion) {
        return habitacionService.crear(habitacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> actualizar(@PathVariable Long id,
                                                 @RequestBody Habitacion datos) {
        return habitacionService.actualizar(id, datos)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Habitacion> cambiarEstado(@PathVariable Long id,
                                                    @RequestParam EstadoHabitacion estado) {
        return habitacionService.cambiarEstado(id, estado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idHabitacion}/asignar-camarera/{idUsuario}")
    public ResponseEntity<Habitacion> asignarCamarera(@PathVariable Long idHabitacion,
                                                      @PathVariable Long idUsuario) {
        return habitacionService.asignarCamarera(idHabitacion, idUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{idHabitacion}/quitar-camarera/{idUsuario}")
    public ResponseEntity<Habitacion> quitarCamarera(@PathVariable Long idHabitacion,
                                                     @PathVariable Long idUsuario) {
        return habitacionService.quitarCamarera(idHabitacion, idUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
