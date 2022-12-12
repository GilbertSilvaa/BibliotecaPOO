
use biblioteca;

# TABELA DE ENDEREÇOS
CREATE TABLE IF NOT EXISTS enderecos (
id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
cep INT NOT NULL,
numero INT NOT NULL,
referencia VARCHAR(255)
);

# TABELA DE USUÁRIOS
CREATE TABLE IF NOT EXISTS usuarios (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50),
id_endereco INT NOT NULL,
CONSTRAINT fk_id_endereco FOREIGN KEY (id_endereco)
REFERENCES biblioteca.enderecos(id)
);

# TABELA DE EDITORAS
CREATE TABLE IF NOT EXISTS editoras (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50),
id_endereco INT NOT NULL,
CONSTRAINT fk_id_endereco_editora FOREIGN KEY (id_endereco)
REFERENCES biblioteca.enderecos(id)
);

# TABELA DE AUTORES
CREATE TABLE IF NOT EXISTS autores (
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50)
);

# TABELA DE LIVROS
CREATE TABLE IF NOT EXISTS livros (
id INT PRIMARY KEY AUTO_INCREMENT,
titulo VARCHAR(50),
id_editora INT NOT NULL,
CONSTRAINT fk_id_editora FOREIGN KEY (id_editora)
REFERENCES biblioteca.editoras(id)
);

# TABELA DE CONTAS
CREATE TABLE IF NOT EXISTS contas (
id INT PRIMARY KEY AUTO_INCREMENT,
email VARCHAR(50),
senha VARCHAR(30)
);


# TABELA DE PEDIDOS
CREATE TABLE IF NOT EXISTS pedidos (
id INT PRIMARY KEY AUTO_INCREMENT,
data_pedido DATETIME,
id_usuario INT NOT NULL,
CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario)
REFERENCES biblioteca.usuarios(id)
);

# TABELA DE ITENS DO PEDIDO
CREATE TABLE IF NOT EXISTS pedido_itens (
id_pedido INT NOT NULL,
id_livro INT NOT NULL,
quantidade_exemplares INT,
CONSTRAINT fk_id_pedido FOREIGN KEY (id_pedido)
REFERENCES biblioteca.pedidos(id),
CONSTRAINT fk_id_livro FOREIGN KEY (id_livro)
REFERENCES biblioteca.livros(id)
);

# TABELA DE RELACIONAMENTO LIVRO - AUTORES
CREATE TABLE IF NOT EXISTS livro_autor (
id_livro INT NOT NULL,
id_autor INT NOT NULL,
CONSTRAINT fk_id_livro_ FOREIGN KEY (id_livro)
REFERENCES biblioteca.livros(id),
CONSTRAINT fk_id_autor_ FOREIGN KEY (id_autor)
REFERENCES biblioteca.autores(id)
);

DELIMITER $$
create procedure P_USUARIO_INSERIR(
	usuario_nome VARCHAR(50),
	endereco_cep INT,
    endereco_numero INT,
    endereco_referencia VARCHAR(255)
)
begin

	insert into enderecos (cep, numero, referencia) values (
		endereco_cep,
        endereco_numero,
        endereco_referencia
    );
    
    insert into usuarios (nome, id_endereco) values (
		usuario_nome,
        (select max(id) from enderecos)
    ); 
    
end $$
DELIMITER ;

DELIMITER $$
create procedure P_USUARIO_ATUALIZAR (
	usuario_id INT,
	usuario_nome VARCHAR(50),
	endereco_cep INT,
    endereco_numero INT,
    endereco_referencia VARCHAR(255)
)
begin

	update usuarios set
    nome = usuario_nome
    where id = usuario_id;
    
    update enderecos set
    cep = endereco_cep,
    numero = endereco_numero,
    referencia = endereco_referencia
    where id = (select id_endereco from usuarios where id = usuario_id);

end$$

DELIMITER $$
create procedure P_USUARIO_DELETAR (
	usuario_id INT
)
begin

    set FOREIGN_KEY_CHECKS=0; -- to disable them
    delete from enderecos
    where id = (select id_endereco from usuarios where id = usuario_id);
    set FOREIGN_KEY_CHECKS=1; -- to re-enable them

	delete from usuarios where id = usuario_id;
    
	delete from pedido_itens where id_pedido in (select id from pedidos where id_usuario = usuario_id);
        
    delete from pedidos where id_usuario = usuario_id;

end$$

DELIMITER $$
create procedure P_AUTOR_DELETAR (
	autor_id INT
)
begin

	delete from autores where id = autor_id;
    
	delete from livro_autor where id_autor = autor_id;

end$$

DELIMITER $$
create procedure P_EDITORA_INSERIR(
	editora_nome VARCHAR(50),
	endereco_cep INT,
    endereco_numero INT,
    endereco_referencia VARCHAR(255)
)
begin

	insert into enderecos (cep, numero, referencia) values (
		endereco_cep,
        endereco_numero,
        endereco_referencia
    );
    
    insert into editoras (nome, id_endereco) values (
		editora_nome,
        (select max(id) from enderecos)
    ); 
    
end $$

DELIMITER $$
create procedure P_EDITORA_ATUALIZAR (
	editora_id INT,
	editora_nome VARCHAR(50),
	endereco_cep INT,
    endereco_numero INT,
    endereco_referencia VARCHAR(255)
)
begin

	update editoras set
    nome = editora_nome
    where id = editora_id;
    
    update enderecos set
    cep = endereco_cep,
    numero = endereco_numero,
    referencia = endereco_referencia
    where id = (select id_endereco from editoras where id = usuario_id);

end$$


DELIMITER $$
create procedure P_EDITORA_DELETAR (
	editora_id INT
)
begin

    set FOREIGN_KEY_CHECKS=0; -- to disable them
	delete from enderecos where id = (select id_endereco from editoras where editoras.id = editora_id);
	set FOREIGN_KEY_CHECKS=1; -- to re-enable them
    
	delete from editoras where id = editora_id;
    
	delete from pedido_itens where id_livro in (select id from livros where id_editora = editora_id);
    
    delete from livros where id_editora = editora_id;
	
end$$


DELIMITER $$
create procedure P_LIVRO_DELETAR (
	livro_id INT
)
begin

	delete from pedido_itens where id_livro = livro_id;
    
    delete from livro_autor where id_livro =  livro_id;
    
    delete from livros where id = livro_id;

end$$