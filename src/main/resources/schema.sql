CREATE TABLE eventos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome_cliente VARCHAR(255) NOT NULL,
    tipo_evento VARCHAR(100) NOT NULL,
    data_evento DATETIME NOT NULL,
    total_convidados_estimado INT,
    status VARCHAR(50) DEFAULT 'AGENDADO',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE mesas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    evento_id BIGINT NOT NULL,
    identificador VARCHAR(100) NOT NULL,
    capacidade_maxima INT NOT NULL,
    FOREIGN KEY (evento_id) REFERENCES eventos(id) ON DELETE CASCADE
);

CREATE TABLE convidados (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    evento_id BIGINT NOT NULL,
    mesa_id BIGINT,
    nome VARCHAR(255) NOT NULL,
    confirmado BOOLEAN DEFAULT FALSE,
    restricoes_alimentares VARCHAR(500),
    FOREIGN KEY (evento_id) REFERENCES eventos(id) ON DELETE CASCADE,
    FOREIGN KEY (mesa_id) REFERENCES mesas(id) ON DELETE SET NULL
);

CREATE TABLE itens_cardapio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    categoria ENUM('ENTRADA', 'VOLANTE', 'PRINCIPAL', 'SOBREMESA', 'ESTACAO_OUTROS') NOT NULL,
    alergenos VARCHAR(500)
);

CREATE TABLE evento_cardapio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    evento_id BIGINT NOT NULL,
    item_cardapio_id BIGINT NOT NULL,
    observacoes TEXT,
    FOREIGN KEY (evento_id) REFERENCES eventos(id) ON DELETE CASCADE,
    FOREIGN KEY (item_cardapio_id) REFERENCES itens_cardapio(id) ON DELETE CASCADE
);
