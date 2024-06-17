create table recuperacao_senha
(
    codigo       bigint generated always as identity
        primary key,
    cpf          text not null
        constraint fk_recuperacao_senha_colaborador
            references colaborador,
    hash         varchar(255),
    data_criacao timestamp,
    expirou      boolean,
    uso          boolean
);

alter table recuperacao_senha
    owner to postgres;

