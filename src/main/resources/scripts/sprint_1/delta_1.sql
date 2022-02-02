--liquibase formatted sql

--changeset Joao Victor:SIS-01
--comment Script de Inicialização do Sistema

CREATE TABLE public.credencial (
	id int8 NOT NULL,
	email varchar NOT NULL,
	senha varchar NOT NULL,
	CONSTRAINT credencial_pk PRIMARY KEY (id)
);

CREATE TABLE public.usuario (
	id int8 NOT NULL,
	email varchar NULL,
	nome varchar NULL,
	credencial int8 NOT NULL,
	CONSTRAINT usuario_pk PRIMARY KEY (id),
	CONSTRAINT usuario_fk FOREIGN KEY (credencial) REFERENCES public.credencial(id)
);

--changeset Joao Victor:SIS-02
-- Tabelas de funcionalidade e perfil
CREATE TABLE public.funcionalidade (
	id int8 NOT NULL,
	nome varchar NOT NULL,
	regra varchar NOT NULL,
	CONSTRAINT funcionalidade_pk PRIMARY KEY (id)
);
CREATE TABLE public.perfil (
	id int8 NOT NULL,
	nome varchar NOT NULL,
	CONSTRAINT perfil_pk PRIMARY KEY (id)
);
CREATE TABLE if not exists public.permissao (
	id serial NOT NULL,
	funcionalidade int8 NOT NULL,
	perfil int8 NOT NULL,
	habilitada boolean NOT NULL,
	CONSTRAINT permissao_pk PRIMARY KEY (id),
	CONSTRAINT permissao_fk FOREIGN KEY (perfil) REFERENCES public.perfil(id),
	CONSTRAINT permissao_fk_1 FOREIGN KEY (funcionalidade) REFERENCES public.funcionalidade(id)
);

--changeset Joao Victor:SIS-02.1
CREATE TABLE public.credencial_perfil (
	credencial int8 NOT NULL,
	perfil int8 NOT NULL,
	CONSTRAINT credencial_perfil_fk FOREIGN KEY (credencial) REFERENCES public.credencial(id),
	CONSTRAINT credencial_perfil_fk_1 FOREIGN KEY (perfil) REFERENCES public.perfil(id)
);


--changeset Joao Victor:SIS-02.3
CREATE SEQUENCE if not exists permissao_id_seq AS integer START 1 OWNED BY permissao.id;
ALTER TABLE permissao ALTER COLUMN id SET DEFAULT nextval('permissao_id_seq');

--changeset Joao Victor:SIS-02.4
CREATE SEQUENCE if not exists perfil_id_seq AS integer START 1 OWNED BY perfil.id;
ALTER TABLE perfil ALTER COLUMN id SET DEFAULT nextval('perfil_id_seq');

--changeset Joao Victor:SIS-3
INSERT INTO funcionalidade (id, nome, regra) VALUES (1, 'Excluir Perfil', 'EXCLUIR_PERFIL');
INSERT INTO funcionalidade (id, nome, regra) VALUES (2, 'Editar Perfil', 'EDITAR_PERFIL');
INSERT INTO funcionalidade (id, nome, regra) VALUES (3, 'Visualizar Perfil', 'VISUALIZAR_PERFIL');
INSERT INTO funcionalidade (id, nome, regra) VALUES (4, 'Cadastrar Perfil', 'CADASTRAR_PERFIL');

INSERT INTO funcionalidade (id, nome, regra) VALUES (5, 'Cadastrar Usuario', 'CADASTRAR_USUARIO');
INSERT INTO funcionalidade (id, nome, regra) VALUES (6, 'Visualizar Usuario', 'VISUALIZAR_USUARIO');
INSERT INTO funcionalidade (id, nome, regra) VALUES (7, 'Deletar Usuario', 'DELETAR_USUARIO');


--changeset Joao Victor:SIS-03.1
INSERT INTO public.perfil (id, nome) VALUES(0, 'ADMINISTRADOR');
INSERT INTO public.permissao (id, funcionalidade, perfil, habilitada) VALUES(0, 4, 0, true);
INSERT INTO public.credencial (id, email, senha) VALUES(0, 'admin@email.com', '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918');
INSERT INTO public.usuario (id, email, nome, credencial) VALUES(0, 'admin@email.com', 'admin', 0);

--changeset Joao Victor:SIS-03.2
INSERT INTO public.permissao (id, funcionalidade, perfil, habilitada) VALUES(1, 5, 0, true);
INSERT INTO public.permissao (id, funcionalidade, perfil, habilitada) VALUES(2, 6, 0, true);
INSERT INTO public.permissao (id, funcionalidade, perfil, habilitada) VALUES(3, 7, 0, true);

--changeset Joao Victor:SIS-03.3

CREATE TABLE if not exists public.sapatos (
	categoria varchar NULL,
	cor varchar NULL,
	preco float8 NOT NULL,
	marca varchar NULL,
	data_cadastro date NOT NULL,
	quantidade_estoque int8 NOT NULL,
	tamanho int8 NOT NULL,
	descricao varchar NULL,
	id serial NOT NULL,
	CONSTRAINT sapatos_pk PRIMARY KEY (id)
);

--changeset Joao Victor:SIS-03.4
INSERT INTO funcionalidade (id, nome, regra) VALUES (8, 'Cadastrar Sapato', 'CADASTRAR_SAPATO');
INSERT INTO funcionalidade (id, nome, regra) VALUES (9, 'Visualizar Sapato', 'VISUALIZAR_SAPATO');
INSERT INTO funcionalidade (id, nome, regra) VALUES (10, 'Deletar Sapato', 'DELETAR_SAPATO');

--changeset Joao Victor:SIS-03.5
INSERT INTO public.permissao (id, funcionalidade, perfil, habilitada) VALUES(4, 8, 0, true);
INSERT INTO public.permissao (id, funcionalidade, perfil, habilitada) VALUES(5, 9, 0, true);
INSERT INTO public.permissao (id, funcionalidade, perfil, habilitada) VALUES(6, 10, 0, true);

--changeset Joao Victor:SIS-03.6
INSERT INTO public.sapatos
(id, categoria, cor, preco, marca, data_cadastro, quantidade_estoque, tamanho, descricao)
VALUES(0, 'Corrida', 'Preto', 299.9, 'Nike', '2022-01-31', 42, 44, 'Versátil e ultra confortável para a academia, treinos leves de corrida, caminhada ou mesmo ocasiões casuais.');
INSERT INTO public.sapatos
(id, categoria, cor, preco, marca, data_cadastro, quantidade_estoque, tamanho, descricao)
VALUES(1, 'Casual', 'Branco', 157.9, 'Qix', '2022-01-31', 26, 40,'Ideal para praticar suas manobras ou sair de rolê, o Tênis Qix Combat Retrô conta com design clássico e versátil para as suas combinações esportivas em grande estilo. Domine as ruas com atitude e conforto usando Qix!');

--changeset Joao Victor:SIS-03.7
CREATE SEQUENCE if not exists sapato_id_seq AS integer START 1 OWNED BY sapatos.id;
ALTER TABLE sapatos ALTER COLUMN id SET DEFAULT nextval('sapato_id_seq');

--changeset Joao Victor:SIS-03.8
INSERT INTO public.credencial_perfil (credencial, perfil) VALUES(0, 0);

INSERT INTO public.permissao (id, funcionalidade, perfil, habilitada) VALUES(7, 1, 0, true);
INSERT INTO public.permissao (id, funcionalidade, perfil, habilitada) VALUES(8, 2, 0, true);
INSERT INTO public.permissao (id, funcionalidade, perfil, habilitada) VALUES(9, 3, 0, true);

