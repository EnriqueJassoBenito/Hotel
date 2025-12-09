package com.example.BackHotel.Incidencia.controller;

import com.example.BackHotel.Habitacion.model.EstadoHabitacion;
import com.example.BackHotel.Habitacion.model.Habitacion;
import com.example.BackHotel.Habitacion.model.HabitacionRepository;
import com.example.BackHotel.Incidencia.model.Incidencia;
import com.example.BackHotel.Incidencia.model.IncidenciaRepository;
import com.example.BackHotel.Rol.model.RolUsuario;
import com.example.BackHotel.Usuario.model.Usuario;
import com.example.BackHotel.Usuario.model.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final HabitacionRepository habitacionRepository;
    private final UsuarioRepository usuarioRepository;

    public IncidenciaService(IncidenciaRepository incidenciaRepository,
                             HabitacionRepository habitacionRepository,
                             UsuarioRepository usuarioRepository) {
        this.incidenciaRepository = incidenciaRepository;
        this.habitacionRepository = habitacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Incidencia> crear(Long habitacionId,
                                      Long camareraId,
                                      String descripcion,
                                      List<String> fotos) {

        Habitacion hab = habitacionRepository.findById(habitacionId).orElse(null);
        Usuario cam = usuarioRepository.findById(camareraId).orElse(null);

        if (hab == null || cam == null || cam.getRol() != RolUsuario.LIMPIEZA) {
            return Optional.empty();
        }

        Incidencia inc = new Incidencia();
        inc.setHabitacion(hab);
        inc.setCamarera(cam);
        inc.setDescripcion(descripcion);
        inc.setCreadaEn(LocalDateTime.now());

        if (fotos != null && !fotos.isEmpty()) {
            inc.getFotos().addAll(fotos.size() > 3 ? fotos.subList(0, 3) : fotos);
        }

        hab.setEstado(EstadoHabitacion.INHABILITADA);
        habitacionRepository.save(hab);

        Incidencia guardada = incidenciaRepository.save(inc);
        return Optional.of(guardada);
    }

    public List<Incidencia> listarTodas() {
        return incidenciaRepository.findAll();
    }

    public List<Incidencia> listarAbiertas() {
        return incidenciaRepository.findByAbiertaTrue();
    }

    public Optional<Incidencia> cerrar(Long incidenciaId,
                                       Long recepcionId,
                                       EstadoHabitacion nuevoEstado) {

        Incidencia inc = incidenciaRepository.findById(incidenciaId).orElse(null);
        Usuario recep = usuarioRepository.findById(recepcionId).orElse(null);

        if (inc == null || recep == null || recep.getRol() != RolUsuario.RECEPCION) {
            return Optional.empty();
        }

        inc.setAbierta(false);
        inc.setRecepcionQueCerro(recep);
        inc.setCerradaEn(LocalDateTime.now());

        Habitacion hab = inc.getHabitacion();
        if (nuevoEstado != null) {
            hab.setEstado(nuevoEstado);
            habitacionRepository.save(hab);
        }

        Incidencia guardada = incidenciaRepository.save(inc);
        return Optional.of(guardada);
    }
}

