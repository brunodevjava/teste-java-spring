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

INSERT INTO public.recuperacao_senha (cpf, hash, data_criacao, expirou, uso) VALUES ('46070952812', '574naa2esc27jeqmoa39l08svr', '2024-05-31 18:06:26.720000', false, false);
INSERT INTO public.recuperacao_senha (cpf, hash, data_criacao, expirou, uso) VALUES ('46070952812', 'shife7al4mqhqf0fqet1il6qgl', '2024-05-31 18:28:11.175000', false, true);
INSERT INTO public.recuperacao_senha (cpf, hash, data_criacao, expirou, uso) VALUES ('46070952812', '93oj81dd5af24j7kcmdv7rv34c', '2024-05-31 18:32:20.559000', false, true);
