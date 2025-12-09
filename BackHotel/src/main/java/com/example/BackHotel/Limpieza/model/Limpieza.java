package com.example.BackHotel.Limpieza.model;

import com.example.BackHotel.Habitacion.model.Habitacion;
import com.example.BackHotel.Usuario.model.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "limpiezas")
public class Limpieza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Habitacion habitacion;

    @ManyToOne(optional = false)
    private Usuario camarera;

    private LocalDateTime fechaHora;

    private boolean tuvoIncidencia;

    // getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Usuario getCamarera() {
        return camarera;
    }

    public void setCamarera(Usuario camarera) {
        this.camarera = camarera;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isTuvoIncidencia() {
        return tuvoIncidencia;
    }

    public void setTuvoIncidencia(boolean tuvoIncidencia) {
        this.tuvoIncidencia = tuvoIncidencia;
    }
}

