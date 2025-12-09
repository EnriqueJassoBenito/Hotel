package com.example.BackHotel.Usuario.model;



import com.example.BackHotel.Rol.model.RolUsuario;

public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String username;
    private RolUsuario rol;
    private boolean activo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nombre, String username, RolUsuario rol, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.rol = rol;
        this.activo = activo;
    }

    public static UsuarioDTO fromEntity(Usuario u) {
        return new UsuarioDTO(
                u.getId(),
                u.getNombre(),
                u.getUsername(),
                u.getRol(),
                u.isActivo()
        );
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // ...
}

