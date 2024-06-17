create table colaborador
(
    cpf          text         not null
        constraint pk_colaborador
            primary key,
    nome         varchar(255) not null,
    senha        varchar(255),
    email        varchar(255),
    data_criacao time with time zone,
    status       boolean
);

alter table colaborador
    owner to postgres;

INSERT INTO public.colaborador (cpf, nome, senha, email, data_criacao, status) VALUES ('00187832013', 'João da Silva', '$2a$10$CJzPW3BMJFTJK7wfWbJnMuvSWXheCSn3Mc4AitsG5MAmiXnXmyQp6', 'teste@gmail.com', null, true);
INSERT INTO public.colaborador (cpf, nome, senha, email, data_criacao, status) VALUES ('12345678987', 'José', '$2a$10$CJzPW3BMJFTJK7wfWbJnMuvSWXheCSn3Mc4AitsG5MAmiXnXmyQp6', 'teste2@gmail.com', null, true);
