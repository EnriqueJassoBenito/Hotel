package com.example.BackHotel.Habitacion.controller;

import com.example.BackHotel.Habitacion.model.EstadoHabitacion;
import com.example.BackHotel.Habitacion.model.Habitacion;
import com.example.BackHotel.Habitacion.model.HabitacionRepository;
import com.example.BackHotel.Rol.model.RolUsuario;
import com.example.BackHotel.Usuario.model.Usuario;
import com.example.BackHotel.Usuario.model.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final UsuarioRepository usuarioRepository;

    public HabitacionService(HabitacionRepository habitacionRepository,
                             UsuarioRepository usuarioRepository) {
        this.habitacionRepository = habitacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<Habitacion> listar() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> buscarPorId(Long id) {
        return habitacionRepository.findById(id);
    }

    public Habitacion crear(Habitacion habitacion) {
        habitacion.setId(null);
        return habitacionRepository.save(habitacion);
    }

    public Optional<Habitacion> actualizar(Long id, Habitacion datos) {
        return habitacionRepository.findById(id)
                .map(h -> {
                    h.setNumero(datos.getNumero());
                    h.setPiso(datos.getPiso());
                    h.setNotas(datos.getNotas());
                    return habitacionRepository.save(h);
                });
    }

    public Optional<Habitacion> cambiarEstado(Long id, EstadoHabitacion estado) {
        return habitacionRepository.findById(id)
                .map(h -> {
                    h.setEstado(estado);
                    if (estado != EstadoHabitacion.DISPONIBLE) {
                        h.setLimpiaHoy(false);
                    }
                    return habitacionRepository.save(h);
                });
    }

    public Optional<Habitacion> asignarCamarera(Long idHabitacion, Long idUsuario) {
        Habitacion hab = habitacionRepository.findById(idHabitacion).orElse(null);
        Usuario user = usuarioRepository.findById(idUsuario).orElse(null);

        if (hab == null || user == null || user.getRol() != RolUsuario.LIMPIEZA) {
            return Optional.empty();
        }

        hab.getCamareras().add(user);
        return Optional.of(habitacionRepository.save(hab));
    }

    public Optional<Habitacion> quitarCamarera(Long idHabitacion, Long idUsuario) {
        Habitacion hab = habitacionRepository.findById(idHabitacion).orElse(null);
        Usuario user = usuarioRepository.findById(idUsuario).orElse(null);

        if (hab == null || user == null) {
            return Optional.empty();
        }

        hab.getCamareras().remove(user);
        return Optional.of(habitacionRepository.save(hab));
    }
}
