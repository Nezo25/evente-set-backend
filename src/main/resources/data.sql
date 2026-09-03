-- Insert Pre-defined Cardápios
INSERT INTO cardapios (nome, is_pre_definido, evento_id) VALUES ('Gold', true, NULL);
INSERT INTO cardapios (nome, is_pre_definido, evento_id) VALUES ('Prata', true, NULL);
INSERT INTO cardapios (nome, is_pre_definido, evento_id) VALUES ('Inverno', true, NULL);

-- Insert ItemCardapio
INSERT INTO itens_cardapio (nome, descricao, categoria, alergenos) VALUES ('Salada Caprese', 'Tomate, mozzarella e manjericão', 'ENTRADA', 'Lactose');
INSERT INTO itens_cardapio (nome, descricao, categoria, alergenos) VALUES ('Risoto de Cogumelos', 'Risoto cremoso com mix de cogumelos', 'PRINCIPAL', 'Lactose');
INSERT INTO itens_cardapio (nome, descricao, categoria, alergenos) VALUES ('Petit Gâteau', 'Bolo de chocolate com recheio cremoso', 'SOBREMESA', 'Glúten, Lactose');
INSERT INTO itens_cardapio (nome, descricao, categoria, alergenos) VALUES ('Bruschetta de Salmão', 'Pão italiano com salmão defumado', 'VOLANTE', 'Glúten, Peixe');

-- Link items to Cardápios
-- Gold (id=1)
INSERT INTO cardapio_itens (cardapio_id, item_cardapio_id) VALUES (1, 1);
INSERT INTO cardapio_itens (cardapio_id, item_cardapio_id) VALUES (1, 2);
INSERT INTO cardapio_itens (cardapio_id, item_cardapio_id) VALUES (1, 3);
-- Prata (id=2)
INSERT INTO cardapio_itens (cardapio_id, item_cardapio_id) VALUES (2, 1);
INSERT INTO cardapio_itens (cardapio_id, item_cardapio_id) VALUES (2, 4);

-- SEED: Eventos
INSERT INTO eventos (nome_cliente, tipo_evento, data_evento, total_convidados_estimado, status) VALUES ('Lucas e Isadora', 'CASAMENTO', '2027-10-15 19:00:00', 150, 'PLANEJAMENTO');
INSERT INTO eventos (nome_cliente, tipo_evento, data_evento, total_convidados_estimado, status) VALUES ('Festa da Empresa XYZ', 'CORPORATIVO', '2027-12-20 20:00:00', 300, 'PLANEJAMENTO');

-- SEED: Mesas do Evento 1
INSERT INTO mesas (identificador, capacidade_maxima, evento_id) VALUES ('Mesa 01 - Família Noiva', 8, 1);
INSERT INTO mesas (identificador, capacidade_maxima, evento_id) VALUES ('Mesa 02 - Padrinhos', 10, 1);
INSERT INTO mesas (identificador, capacidade_maxima, evento_id) VALUES ('Mesa 03 - Amigos', 6, 1);

-- SEED: Mesas do Evento 2
INSERT INTO mesas (identificador, capacidade_maxima, evento_id) VALUES ('Mesa VIP Diretoria', 5, 2);
INSERT INTO mesas (identificador, capacidade_maxima, evento_id) VALUES ('Mesa Comercial', 12, 2);

-- SEED: Convidados Evento 1 (Sem mesa)
INSERT INTO convidados (nome, confirmado, restricoes_alimentares, evento_id, cardapio_id) VALUES ('João Silva', true, 'Celíaco', 1, 1);
INSERT INTO convidados (nome, confirmado, restricoes_alimentares, evento_id, cardapio_id) VALUES ('Maria Souza', true, 'Nenhuma', 1, 1);
INSERT INTO convidados (nome, confirmado, restricoes_alimentares, evento_id, cardapio_id) VALUES ('Carlos Mendes', true, 'Vegano, Alergia a amendoim', 1, 2);
INSERT INTO convidados (nome, confirmado, restricoes_alimentares, evento_id, cardapio_id) VALUES ('Ana Costa', true, 'Nenhuma', 1, 2);
INSERT INTO convidados (nome, confirmado, restricoes_alimentares, evento_id, cardapio_id) VALUES ('Vitor Igor', true, 'Intolerância à lactose', 1, 1);

-- SEED: Convidados Evento 2 (Sem mesa)
INSERT INTO convidados (nome, confirmado, restricoes_alimentares, evento_id, cardapio_id) VALUES ('Roberto CEO', true, 'Nenhuma', 2, 1);
INSERT INTO convidados (nome, confirmado, restricoes_alimentares, evento_id, cardapio_id) VALUES ('Julia RH', true, 'Vegetariana', 2, 2);
