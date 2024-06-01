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

INSERT INTO public.colaborador (cpf, nome, senha, email, data_criacao, status) VALUES ('00187832013', 'João da Silva', '$2a$10$CJzPW3BMJFTJK7wfWbJnMuvSWXheCSn3Mc4AitsG5MAmiXnXmyQp6', 'sversuttibrteles@gmail.com', null, true);
INSERT INTO public.colaborador (cpf, nome, senha, email, data_criacao, status) VALUES ('12345678987', 'José', '$2a$10$CJzPW3BMJFTJK7wfWbJnMuvSWXheCSn3Mc4AitsG5MAmiXnXmyQp6', 'brunodeveloper2022@gmail.com', null, true);
INSERT INTO public.colaborador (cpf, nome, senha, email, data_criacao, status) VALUES ('20408216000', 'Bruno Sversutti teste', '$2a$10$26VduNojFGYTJX4cyqCEaOuUO1FrziP0YL3kn5udqRlvw7uV/TB/2', 'logmeinremoto55@hotmail.com', '18:19:09 +00:00', false);
INSERT INTO public.colaborador (cpf, nome, senha, email, data_criacao, status) VALUES ('46070952812', 'Bruno Sversutti', '$2a$10$oiDpQfB9YWIQbcT1KKFwMeJbyItZHoh3X0ThqRcMnmCqBh50hAL8e', 'sversuttibruno@gmail.com', '04:17:26 +00:00', true);
