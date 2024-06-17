create table marcacao_vinculo_inicio_fim
(
    codigo              bigint not null
        constraint pk_marcacao_vinculo_inicio_fim
            primary key,
    cod_marcacao_inicio bigint not null
        constraint unico_codigo_inicio
            unique
        constraint fk_marcacao_vinculo_marcacao_inicio
            references marcacao,
    cod_marcacao_fim    bigint not null
        constraint unico_codigo_fim
            unique
        constraint fk_marcacao_vinculo_marcacao_fim
            references marcacao
);

alter table marcacao_vinculo_inicio_fim
    owner to postgres;

INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (26, 356598, 356666);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (27, 345266, 345275);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (28, 345343, 346884);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (29, 345132, 346885);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (30, 348498, 348536);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (31, 348497, 348550);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (32, 348741, 348849);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (33, 348537, 348538);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (34, 348742, 348758);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (35, 348788, 348808);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (36, 348823, 348830);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (37, 351077, 351149);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (38, 351221, 351237);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (39, 351264, 351280);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (40, 351281, 351317);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (41, 351075, 351318);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (42, 354659, 354760);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (43, 354925, 354938);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (44, 354870, 354898);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (45, 354653, 354997);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (46, 356599, 356606);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (47, 356612, 356618);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (48, 356634, 356642);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (49, 356656, 356659);
INSERT INTO public.marcacao_vinculo_inicio_fim (codigo, cod_marcacao_inicio, cod_marcacao_fim) VALUES (50, 356660, 356661);
