package com.credenciamento.config;

import com.credenciamento.model.Credencial;
import com.credenciamento.model.Usuario;
import com.credenciamento.repository.CredencialRepository;
import com.credenciamento.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UsuarioRepository usuarioRepository, CredencialRepository credencialRepository) {
        return args -> {
            Usuario usuario = new Usuario("João Silva", "joao@email.com", "12345678900", "123456");
            usuarioRepository.save(usuario);

            Credencial credencial = new Credencial(usuario, "Acesso", "CRED-001", "ATIVO");
            credencialRepository.save(credencial);
        };
    }
}
