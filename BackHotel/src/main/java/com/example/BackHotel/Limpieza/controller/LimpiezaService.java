package com.example.BackHotel.Limpieza.controller;

import com.example.BackHotel.Habitacion.model.EstadoHabitacion;
import com.example.BackHotel.Habitacion.model.Habitacion;
import com.example.BackHotel.Habitacion.model.HabitacionRepository;
import com.example.BackHotel.Limpieza.model.Limpieza;
import com.example.BackHotel.Limpieza.model.LimpiezaRepository;
import com.example.BackHotel.Rol.model.RolUsuario;
import com.example.BackHotel.Usuario.model.Usuario;
import com.example.BackHotel.Usuario.model.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LimpiezaService {

    private final LimpiezaRepository limpiezaRepository;
    private final HabitacionRepository habitacionRepository;
    private final UsuarioRepository usuarioRepository;

    public LimpiezaService(LimpiezaRepository limpiezaRepository,
                           HabitacionRepository habitacionRepository,
                           UsuarioRepository usuarioRepository) {
        this.limpiezaRepository = limpiezaRepository;
        this.habitacionRepository = habitacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Limpieza> marcarLimpia(Long habitacionId, Long camareraId) {
        Habitacion hab = habitacionRepository.findById(habitacionId).orElse(null);
        Usuario cam = usuarioRepository.findById(camareraId).orElse(null);

        if (hab == null || cam == null || cam.getRol() != RolUsuario.LIMPIEZA) {
            return Optional.empty();
        }

        Limpieza limpieza = new Limpieza();
        limpieza.setHabitacion(hab);
        limpieza.setCamarera(cam);
        limpieza.setFechaHora(LocalDateTime.now());
        limpieza.setTuvoIncidencia(false);

        hab.setEstado(EstadoHabitacion.DISPONIBLE);
        hab.setLimpiaHoy(true);

        habitacionRepository.save(hab);
        Limpieza guardada = limpiezaRepository.save(limpieza);

        return Optional.of(guardada);
    }

    public List<Limpieza> historialPorHabitacion(Long habitacionId) {
        return limpiezaRepository.findByHabitacionId(habitacionId);
    }

    public List<Limpieza> historialPorCamarera(Long camareraId) {
        return limpiezaRepository.findByCamareraId(camareraId);
    }
    public List<Limpieza> listarUltimos(int limite) {
        // De
        // "limite" más adelante, se cambia a Pageable.
        return limpiezaRepository.findTop50ByOrderByFechaHoraDesc();
    }
}

