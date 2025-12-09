package com.example.BackHotel.Usuario.model;

import com.example.BackHotel.Rol.model.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
    List<Usuario> findByRol(RolUsuario rol);
    boolean existsByUsername(String username);
}
