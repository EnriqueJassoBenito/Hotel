package com.example.BackHotel.config;

import com.example.BackHotel.Rol.model.RolUsuario;
import com.example.BackHotel.Usuario.model.Usuario;
import com.example.BackHotel.Usuario.model.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDefaultUsers(UsuarioRepository usuarioRepository) {
        return args -> {

            if (!usuarioRepository.existsByUsername("admin")) {
                Usuario recep = new Usuario();
                recep.setNombre("Recepción Admin");
                recep.setUsername("admin");
                recep.setPassword("1234");
                recep.setRol(RolUsuario.RECEPCION);
                recep.setActivo(true);

                usuarioRepository.save(recep);
            }

            if (!usuarioRepository.existsByUsername("camarera")) {
                Usuario cam = new Usuario();
                cam.setNombre("Camarera Default");
                cam.setUsername("camarera");
                cam.setPassword("1234");
                cam.setRol(RolUsuario.LIMPIEZA);
                cam.setActivo(true);

                usuarioRepository.save(cam);
            }
        };
    }

}
