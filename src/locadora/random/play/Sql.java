/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locadora.random.play;

/**
 *
 * @author kauan
 */
public class Sql {
/*
-- Database: random-play
-- DROP DATABASE IF EXISTS "random_play";

CREATE DATABASE "random_play"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Table: public.cliente
-- DROP TABLE IF EXISTS public.cliente;

CREATE TABLE IF NOT EXISTS public.cliente
(
    id SERIAL NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    cpf character varying(14) COLLATE pg_catalog."default" NOT NULL,
    endereco character varying(100) COLLATE pg_catalog."default" NOT NULL,
    data_nascimento character varying(10) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT cliente_pkey PRIMARY KEY (id)
);

-- Table: public.filme
-- DROP TABLE IF EXISTS public.filme;

CREATE TABLE IF NOT EXISTS public.filme
(
    id SERIAL NOT NULL,
    titulo character varying(100) COLLATE pg_catalog."default" NOT NULL,
    autor character varying(100) COLLATE pg_catalog."default" NOT NULL,
    descricao character varying(255) COLLATE pg_catalog."default" NOT NULL,
    valor_locacao numeric(10,2) NOT NULL,
    qntd_estoque integer NOT NULL,
    duracao integer NOT NULL,
    CONSTRAINT filme_pkey PRIMARY KEY (id)
);

-- Table: public.funcionario
-- DROP TABLE IF EXISTS public.funcionario;

CREATE TABLE IF NOT EXISTS public.funcionario
(
    id SERIAL NOT NULL,
    nome character varying(100) COLLATE pg_catalog."default",
    login character varying(50) COLLATE pg_catalog."default",
    senha character varying(50) COLLATE pg_catalog."default",
    data_nascimento character varying(10) COLLATE pg_catalog."default",
    cpf character varying(14) COLLATE pg_catalog."default",
    CONSTRAINT funcionario_pkey PRIMARY KEY (id)
);

-- Table: public.genero
-- DROP TABLE IF EXISTS public.genero;

CREATE TABLE IF NOT EXISTS public.genero
(
    id SERIAL NOT NULL,
    descricao character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT genero_pkey PRIMARY KEY (id)
);

-- Table: public.generos_filme
-- DROP TABLE IF EXISTS public.generos_filme;

CREATE TABLE IF NOT EXISTS public.generos_filme
(
    id SERIAL NOT NULL,
    id_filme integer,
    id_genero integer
);

-- Table: public.itens_locacao
-- DROP TABLE IF EXISTS public.itens_locacao;

CREATE TABLE IF NOT EXISTS public.itens_locacao
(
    id SERIAL NOT NULL,
    id_locacao integer,
    id_filme integer,
    valor_unitario double precision,
    CONSTRAINT itens_locacao_pkey PRIMARY KEY (id)
);

-- Table: public.locacao
-- DROP TABLE IF EXISTS public.locacao;

CREATE TABLE IF NOT EXISTS public.locacao
(
    id SERIAL NOT NULL,
    data_locacao date DEFAULT CURRENT_DATE,
    data_devolucao date,
    valor_total numeric(10,2),
    id_cliente integer NOT NULL,
    id_funcionario integer NOT NULL,
    data_devolucao_real date,
    CONSTRAINT locacao_pkey PRIMARY KEY (id),
    CONSTRAINT locacao_id_cliente_fkey FOREIGN KEY (id_cliente)
        REFERENCES public.cliente (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT locacao_id_funcionario_fkey FOREIGN KEY (id_funcionario)
        REFERENCES public.funcionario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
*/
}
