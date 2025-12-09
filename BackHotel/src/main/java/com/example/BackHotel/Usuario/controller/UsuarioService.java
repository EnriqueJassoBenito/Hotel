package com.example.BackHotel.Usuario.controller;


import com.example.BackHotel.Rol.model.RolUsuario;
import com.example.BackHotel.Usuario.model.Usuario;
import com.example.BackHotel.Usuario.model.UsuarioDTO;
import com.example.BackHotel.Usuario.model.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioDTO::fromEntity);
    }

    public UsuarioDTO crear(Usuario usuario) {
        usuario.setId(null);
        Usuario guardado = usuarioRepository.save(usuario);
        return UsuarioDTO.fromEntity(guardado);
    }

    public Optional<UsuarioDTO> actualizar(Long id, Usuario datos) {
        return usuarioRepository.findById(id)
                .map(u -> {
                    u.setNombre(datos.getNombre());
                    u.setUsername(datos.getUsername());
                    if (datos.getPassword() != null && !datos.getPassword().isBlank()) {
                        u.setPassword(datos.getPassword());
                    }
                    u.setRol(datos.getRol());
                    u.setActivo(datos.isActivo());
                    return UsuarioDTO.fromEntity(usuarioRepository.save(u));
                });
    }

    public boolean eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            return false;
        }
        usuarioRepository.deleteById(id);
        return true;
    }

    public List<UsuarioDTO> listarCamareras() {
        return usuarioRepository.findByRol(RolUsuario.LIMPIEZA)
                .stream()
                .map(UsuarioDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
