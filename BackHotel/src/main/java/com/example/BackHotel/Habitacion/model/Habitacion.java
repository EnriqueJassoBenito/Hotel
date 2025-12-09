package com.example.BackHotel.Habitacion.model;

import com.example.BackHotel.Usuario.model.Usuario;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numero;

    private Integer piso;

    @Enumerated(EnumType.STRING)
    private EstadoHabitacion estado = EstadoHabitacion.DISPONIBLE;

    private boolean limpiaHoy = false;

    @ManyToMany
    @JoinTable(
            name = "habitacion_camarera",
            joinColumns = @JoinColumn(name = "habitacion_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> camareras = new HashSet<>();

    private String notas;

    // getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public EstadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoHabitacion estado) {
        this.estado = estado;
    }

    public boolean isLimpiaHoy() {
        return limpiaHoy;
    }

    public void setLimpiaHoy(boolean limpiaHoy) {
        this.limpiaHoy = limpiaHoy;
    }

    public Set<Usuario> getCamareras() {
        return camareras;
    }

    public void setCamareras(Set<Usuario> camareras) {
        this.camareras = camareras;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
}
