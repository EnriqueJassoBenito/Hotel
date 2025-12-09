package com.example.BackHotel.Incidencia.model;

import com.example.BackHotel.Habitacion.model.Habitacion;
import com.example.BackHotel.Usuario.model.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "incidencias")
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Habitacion habitacion;

    @ManyToOne(optional = false)
    private Usuario camarera;

    private LocalDateTime creadaEn;

    @Column(nullable = false, length = 1000)
    private String descripcion;

    @ElementCollection
    @CollectionTable(name = "incidencia_fotos", joinColumns = @JoinColumn(name = "incidencia_id"))
    @Column(name = "foto_url", columnDefinition = "LONGTEXT")
    private List<String> fotos = new ArrayList<>();

    private boolean abierta = true;

    @ManyToOne
    private Usuario recepcionQueCerro;

    private LocalDateTime cerradaEn;

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

    public LocalDateTime getCreadaEn() {
        return creadaEn;
    }

    public void setCreadaEn(LocalDateTime creadaEn) {
        this.creadaEn = creadaEn;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public boolean isAbierta() {
        return abierta;
    }

    public void setAbierta(boolean abierta) {
        this.abierta = abierta;
    }

    public Usuario getRecepcionQueCerro() {
        return recepcionQueCerro;
    }

    public void setRecepcionQueCerro(Usuario recepcionQueCerro) {
        this.recepcionQueCerro = recepcionQueCerro;
    }

    public LocalDateTime getCerradaEn() {
        return cerradaEn;
    }

    public void setCerradaEn(LocalDateTime cerradaEn) {
        this.cerradaEn = cerradaEn;
    }
}
