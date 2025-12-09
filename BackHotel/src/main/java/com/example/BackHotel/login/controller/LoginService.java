package com.example.BackHotel.login.controller;

import com.example.BackHotel.Usuario.controller.UsuarioService;
import com.example.BackHotel.Usuario.model.Usuario;
import com.example.BackHotel.login.model.LoginRequest;
import com.example.BackHotel.login.model.LoginResponse;
import com.example.BackHotel.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    public LoginService(UsuarioService usuarioService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    public Optional<LoginResponse> login(LoginRequest request) {
        return usuarioService.buscarPorUsername(request.getUsername())
                .filter(Usuario::isActivo)
                // ojo: aquí sigues usando password plano; luego puedes cambiar a BCrypt
                .filter(u -> u.getPassword().equals(request.getPassword()))
                .map(this::toResponseWithToken);
    }

    private LoginResponse toResponseWithToken(Usuario u) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("rol", u.getRol().name());
        claims.put("id", u.getId());

        String token = jwtUtil.generateToken(u.getUsername(), claims);

        return new LoginResponse(
                token,
                u.getId(),
                u.getNombre(),
                u.getUsername(),
                u.getRol(),
                u.isActivo()
        );
    }
}
