package com.example.BackHotel.Limpieza.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LimpiezaRepository extends JpaRepository<Limpieza, Long> {
    List<Limpieza> findByHabitacionId(Long habitacionId);
    List<Limpieza> findByCamareraId(Long camareraId);
    // 👇 NUEVO: últimos 50 registros (ordenados por fecha descendente)
    List<Limpieza> findTop50ByOrderByFechaHoraDesc();
}
