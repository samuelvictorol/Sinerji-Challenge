
-- Insira endereços
INSERT INTO endereco (estado, cep, cidade, logradouro, numero) VALUES ('RJ', '87654321', 'Rio de Janeiro', 'Avenida Atlântica', 1234);

-- Insira pessoas

INSERT INTO pessoa (nome, idade, sexo) VALUES ('Maria de Souza', '1995-03-25', 'FEM');

-- Atualiza a tabela de endereço com os IDs das pessoas correspondentes
UPDATE endereco SET pessoa_id = 1 WHERE id = 1;
UPDATE pessoa SET endereco_id = 1 WHERE id = 1;