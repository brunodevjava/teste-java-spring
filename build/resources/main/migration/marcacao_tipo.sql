create table marcacao_tipo
(
    codigo                    bigint not null
        constraint pk_intervalo_tipo
            primary key,
    nome                      text   not null,
    tempo_recomendado_minutos bigint
);

alter table marcacao_tipo
    owner to postgres;

INSERT INTO public.marcacao_tipo (codigo, nome, tempo_recomendado_minutos) VALUES (28, 'Refeição', 30);
INSERT INTO public.marcacao_tipo (codigo, nome, tempo_recomendado_minutos) VALUES (30, 'Espera', 60);
INSERT INTO public.marcacao_tipo (codigo, nome, tempo_recomendado_minutos) VALUES (56, 'Descanso', 60);
INSERT INTO public.marcacao_tipo (codigo, nome, tempo_recomendado_minutos) VALUES (29, 'Jornada', 720);
