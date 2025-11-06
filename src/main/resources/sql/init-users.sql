-- Inicialização de usuários padrão
-- Usuários: admin@email.com (senha: 123456, ROLE_ADMIN)
--          usuario@email.com (senha: 123456, ROLE_CLIENTE)
-- Atenção: as senhas aqui já estão em hash BCrypt

INSERT INTO usuarios (id, username, password, role)
VALUES (1000, 'admin@email.com', '$2a$12$wTid126TXSdUlcuQq/vUNeUMtKFqtyztbkmdZ19jWBZ1di5ap8fkC', 'ROLE_ADMIN');

INSERT INTO usuarios (id, username, password, role)
VALUES (1001, 'usuario@email.com', '$2a$12$wTid126TXSdUlcuQq/vUNeUMtKFqtyztbkmdZ19jWBZ1di5ap8fkC', 'ROLE_CLIENTE');

