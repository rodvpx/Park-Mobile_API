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
public class DevDataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DevDataSeeder.class);

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) {
        // Usuários padrão para desenvolvimento/local
        seedUser("admin@local", "123456", Usuario.Role.ROLE_ADMIN);
        seedUser("cliente@local", "123456", Usuario.Role.ROLE_CLIENTE);
    }

    private void seedUser(String username, String rawPassword, Usuario.Role role) {
        if (usuarioRepository.findByUsername(username).isPresent()) {
            log.debug("Usuário {} já existe, pulando seed", username);
            return;
        }
        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(rawPassword); // será codificada pelo UsuarioService.salvar
        u.setRole(role);
        try {
            usuarioService.salvar(u);
            log.info("Usuário seed criado: {}", username);
        } catch (Exception ex) {
            // Se houver qualquer problema (ex: concorrência, DB indisponível), não impedimos a aplicação de subir
            log.warn("Não foi possível criar usuário seed {}: {}", username, ex.getMessage());
        }
    }
}
