package com.example.BackHotel.Incidencia.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    List<Incidencia> findByAbiertaTrue();

    List<Incidencia> findByHabitacionId(Long habitacionId);
}
