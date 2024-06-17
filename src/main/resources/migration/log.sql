create table log
(
    codigo       bigint generated always as identity
        primary key,
    nome_tabela  varchar(255)             not null,
    acao         varchar(255)             not null,
    data_criacao timestamp with time zone not null,
    cpf          text                     not null
        constraint fk_log_colaborador
            references colaborador
);

alter table log
    owner to postgres;

