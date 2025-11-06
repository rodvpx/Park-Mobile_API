package com.lhcampos.demo_park_api.config;

import com.lhcampos.demo_park_api.entity.Usuario;
import com.lhcampos.demo_park_api.repository.UsuarioRepository;
import com.lhcampos.demo_park_api.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InitialDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(InitialDataLoader.class);

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {
        seedIfNotExists("admin@email.com", "123456", Usuario.Role.ROLE_ADMIN);
        seedIfNotExists("usuario@email.com", "123456", Usuario.Role.ROLE_CLIENTE);
    }

    private void seedIfNotExists(String username, String rawPassword, Usuario.Role role) {
        try {
            if (usuarioRepository.findByUsername(username).isPresent()) {
                log.debug("Usuario {} ja existe, pulando.", username);
                return;
            }
            Usuario u = new Usuario();
            u.setUsername(username);
            u.setPassword(rawPassword);
            u.setRole(role);
            usuarioService.salvar(u);
            log.info("Usuario seed criado: {}", username);
        } catch (Exception ex) {
            log.warn("Nao foi possivel criar usuario {}: {}", username, ex.getMessage());
        }
    }
}

