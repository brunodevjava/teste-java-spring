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

INSERT INTO public.log (nome_tabela, acao, data_criacao, cpf) VALUES ('COLABORADOR', 'CREATE', '2024-05-30 04:17:26.798865 +00:00', '46070952812');
INSERT INTO public.log (nome_tabela, acao, data_criacao, cpf) VALUES ('COLABORADOR', 'UPDATE', '2024-05-30 17:45:30.386245 +00:00', '46070952812');
INSERT INTO public.log (nome_tabela, acao, data_criacao, cpf) VALUES ('COLABORADOR', 'UPDATE', '2024-05-30 17:48:57.336167 +00:00', '46070952812');
INSERT INTO public.log (nome_tabela, acao, data_criacao, cpf) VALUES ('COLABORADOR', 'UPDATE', '2024-05-30 18:02:40.528925 +00:00', '46070952812');
INSERT INTO public.log (nome_tabela, acao, data_criacao, cpf) VALUES ('COLABORADOR', 'CREATE', '2024-05-30 18:19:09.817381 +00:00', '20408216000');
INSERT INTO public.log (nome_tabela, acao, data_criacao, cpf) VALUES ('COLABORADOR', 'DELETE', '2024-05-30 18:19:50.211259 +00:00', '20408216000');
