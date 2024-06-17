create table marcacao
(
    codigo             bigint                   not null
        constraint pk_marcacao
            primary key,
    cod_tipo_marcacao  bigint                   not null
        constraint fk_marcacao_marcacao_tipo
            references marcacao_tipo,
    cpf_colaborador    text                     not null
        constraint fk_intervalo_colaborador
            references colaborador,
    data_hora_marcacao timestamp with time zone not null,
    tipo_marcacao      varchar(255)
        constraint tipo_marcacao
            check ((tipo_marcacao)::text = ANY (ARRAY ['MARCACAO_INICIO'::text, 'MARCACAO_FIM'::text]))
);

alter table marcacao
    owner to postgres;

INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (356598, 29, '00187832013', '2019-03-10 18:16:48.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (356666, 29, '00187832013', '2019-03-11 03:23:29.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (324456, 30, '12345678987', '2019-01-02 22:27:08.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (345132, 29, '00187832013', '2019-02-28 21:57:39.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (345266, 28, '00187832013', '2019-03-01 05:38:44.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (345275, 28, '00187832013', '2019-03-01 06:15:51.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (345343, 56, '00187832013', '2019-03-01 11:07:02.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (346884, 56, '00187832013', '2019-03-01 19:10:10.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (346885, 29, '00187832013', '2019-03-01 19:10:24.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348497, 29, '00187832013', '2019-03-02 21:22:32.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348498, 30, '00187832013', '2019-03-02 21:22:43.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348536, 30, '00187832013', '2019-03-02 22:40:00.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348537, 30, '00187832013', '2019-03-03 00:09:54.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348538, 30, '00187832013', '2019-03-03 00:27:46.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348550, 29, '00187832013', '2019-03-03 01:39:25.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348741, 29, '00187832013', '2019-03-04 00:19:06.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348742, 30, '00187832013', '2019-03-04 00:19:23.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348758, 30, '00187832013', '2019-03-04 01:29:56.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348788, 30, '00187832013', '2019-03-04 05:00:44.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348808, 30, '00187832013', '2019-03-04 05:41:35.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348823, 28, '00187832013', '2019-03-04 06:43:42.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348830, 28, '00187832013', '2019-03-04 07:42:45.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (348849, 29, '00187832013', '2019-03-04 09:50:03.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (351075, 29, '00187832013', '2019-03-06 18:40:35.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (351077, 30, '00187832013', '2019-03-06 18:45:42.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (351149, 30, '00187832013', '2019-03-06 21:13:03.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (351221, 28, '00187832013', '2019-03-06 23:35:53.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (351237, 28, '00187832013', '2019-03-07 00:06:20.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (351264, 30, '00187832013', '2019-03-07 01:29:28.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (351280, 30, '00187832013', '2019-03-07 02:25:02.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (351281, 56, '00187832013', '2019-03-07 02:25:22.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (351317, 56, '00187832013', '2019-03-07 05:47:38.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (351318, 29, '00187832013', '2019-03-07 05:47:55.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (354653, 29, '00187832013', '2019-03-08 18:14:59.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (354659, 30, '00187832013', '2019-03-08 18:15:36.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (354760, 30, '00187832013', '2019-03-08 19:48:08.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (354870, 28, '00187832013', '2019-03-08 23:05:36.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (354898, 28, '00187832013', '2019-03-08 23:37:04.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (354925, 30, '00187832013', '2019-03-09 00:30:22.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (354938, 30, '00187832013', '2019-03-09 01:07:06.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (354997, 29, '00187832013', '2019-03-09 04:26:14.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (356599, 30, '00187832013', '2019-03-10 18:17:00.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (356606, 30, '00187832013', '2019-03-10 19:26:03.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (356612, 30, '00187832013', '2019-03-10 20:42:28.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (356618, 30, '00187832013', '2019-03-10 21:14:19.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (356634, 30, '00187832013', '2019-03-10 22:28:35.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (356642, 30, '00187832013', '2019-03-10 23:10:29.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (356656, 28, '00187832013', '2019-03-11 00:15:12.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (356659, 28, '00187832013', '2019-03-11 00:55:09.000000 +00:00', 'MARCACAO_FIM');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (356660, 30, '00187832013', '2019-03-11 01:21:44.000000 +00:00', 'MARCACAO_INICIO');
INSERT INTO public.marcacao (codigo, cod_tipo_marcacao, cpf_colaborador, data_hora_marcacao, tipo_marcacao) VALUES (356661, 30, '00187832013', '2019-03-11 01:52:56.000000 +00:00', 'MARCACAO_FIM');
