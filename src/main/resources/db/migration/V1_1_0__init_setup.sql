CREATE TABLE conectacausa.ability (
                                      id integer NOT NULL,
                                      description text NOT NULL
);


ALTER TABLE conectacausa.ability OWNER TO postgres;

CREATE TABLE conectacausa.cause_type (
                                         id integer NOT NULL,
                                         description text NOT NULL
);


ALTER TABLE conectacausa.cause_type OWNER TO postgres;

CREATE TABLE conectacausa.opportunity (
                                          id integer NOT NULL,
                                          description text NOT NULL,
                                          organization_id integer,
                                          duration integer NOT NULL,
                                          cause_type_id integer,
                                          hour text NOT NULL
);


ALTER TABLE conectacausa.opportunity OWNER TO postgres;

CREATE TABLE conectacausa.opportunity_ability (
                                                  opportunity_id integer NOT NULL,
                                                  ability_id integer NOT NULL
);


ALTER TABLE conectacausa.opportunity_ability OWNER TO postgres;

CREATE TABLE conectacausa.organization (
                                           id integer NOT NULL,
                                           name text NOT NULL,
                                           email text NOT NULL,
                                           address_number text,
                                           address_detail text,
                                           zip_code text NOT NULL
);


ALTER TABLE conectacausa.organization OWNER TO postgres;

CREATE SEQUENCE conectacausa.seq_ability
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE conectacausa.seq_ability OWNER TO postgres;

CREATE SEQUENCE conectacausa.seq_cause_type
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE conectacausa.seq_cause_type OWNER TO postgres;

CREATE SEQUENCE conectacausa.seq_opportunity
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE conectacausa.seq_opportunity OWNER TO postgres;

CREATE SEQUENCE conectacausa.seq_organization
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE conectacausa.seq_organization OWNER TO postgres;

CREATE SEQUENCE conectacausa.seq_user
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE conectacausa.seq_user OWNER TO postgres;

CREATE TABLE conectacausa."user" (
                                     id integer NOT NULL,
                                     email text NOT NULL,
                                     password text NOT NULL,
                                     name text NOT NULL,
                                     address_number text NOT NULL,
                                     address_detail text,
                                     zip_code text NOT NULL,
                                     availability_start_time text NOT NULL,
                                     availability_end_time text NOT NULL
);


ALTER TABLE conectacausa."user" OWNER TO postgres;

CREATE TABLE conectacausa.user_ability (
                                           user_id integer NOT NULL,
                                           ability_id integer NOT NULL
);


ALTER TABLE conectacausa.user_ability OWNER TO postgres;

CREATE TABLE conectacausa.user_opportunity (
                                               user_id integer NOT NULL,
                                               opportunity_id integer NOT NULL
);


ALTER TABLE conectacausa.user_opportunity OWNER TO postgres;

CREATE TABLE conectacausa.zip_code (
                                       zip_code text NOT NULL,
                                       street text NOT NULL,
                                       neighborhood text NOT NULL,
                                       city text NOT NULL,
                                       state_code text NOT NULL,
                                       country_code text NOT NULL
);


ALTER TABLE conectacausa.zip_code OWNER TO postgres;

INSERT INTO conectacausa.ability (id, description) VALUES
                                                       (1, 'Organização e Planejamento de Eventos'),
                                                       (2, 'Comunicação e Relações Públicas'),
                                                       (3, 'Domínio de Redes Sociais e Marketing Digital'),
                                                       (4, 'Conhecimento em Design Gráfico (Ex: Canva, Photoshop)'),
                                                       (5, 'Habilidades de Ensino ou Treinamento'),
                                                       (6, 'Trabalho Manual (Ex: Construção, Jardinagem, Artesanato)'),
                                                       (7, 'Capacidade de Coleta e Análise de Dados'),
                                                       (8, 'Suporte Administrativo ou Escritório'),
                                                       (9, 'Primeiros Socorros ou Suporte de Saúde'),
                                                       (10, 'Condução de Veículos e Logística');


INSERT INTO conectacausa.cause_type (id, description) VALUES
                                                          (1, 'Combate à Fome'),
                                                          (2, 'Preservação Ambiental'),
                                                          (3, 'Incentivo à Educação e Cultura'),
                                                          (4, 'Promoção da Saúde'),
                                                          (5, 'Defesa dos Direitos Humanos'),
                                                          (6, 'Proteção Animal'),
                                                          (7, 'Desenvolvimento Comunitário'),
                                                          (8, 'Apoio a Crianças e Adolescentes'),
                                                          (9, 'Combate ao Racismo e Desigualdade'),
                                                          (10, 'Assistência a Pessoas em Situação de Vulnerabilidade');


INSERT INTO conectacausa.zip_code(
    zip_code, street, neighborhood, city, state_code, country_code
) VALUES
      ('44001002', 'Rua Exemplo 1', 'Centro', 'Feira de Santana', 'BA', 'BR'),
      ('44002110', 'Avenida Exemplo 2', 'Kalilândia', 'Feira de Santana', 'BA', 'BR'),
      ('44004224', 'Rua Exemplo 3', 'Muchila', 'Feira de Santana', 'BA', 'BR'),
      ('44006146', 'Rua Exemplo 4', 'Capuchinhos', 'Feira de Santana', 'BA', 'BR'),
      ('44009014', 'Rua Exemplo 5', 'Sobradinho', 'Feira de Santana', 'BA', 'BR'),
      ('44010160', 'Rua Exemplo 6', 'SIM', 'Feira de Santana', 'BA', 'BR'),
      ('44013214', 'Rua Exemplo 7', 'Santa Mônica', 'Feira de Santana', 'BA', 'BR'),
      ('44015240', 'Rua Exemplo 8', 'Mangabeira', 'Feira de Santana', 'BA', 'BR'),
      ('44020224', 'Rua Exemplo 9', 'Tomba', 'Feira de Santana', 'BA', 'BR'),
      ('44032900', 'Rua Exemplo 10', 'Aviário', 'Feira de Santana', 'BA', 'BR');


ALTER TABLE ONLY conectacausa.ability
    ADD CONSTRAINT ability_pkey PRIMARY KEY (id);

ALTER TABLE ONLY conectacausa.cause_type
    ADD CONSTRAINT cause_type_pkey PRIMARY KEY (id);

ALTER TABLE ONLY conectacausa.opportunity_ability
    ADD CONSTRAINT opportunity_ability_pkey PRIMARY KEY (opportunity_id, ability_id);

ALTER TABLE ONLY conectacausa.opportunity
    ADD CONSTRAINT opportunity_pkey PRIMARY KEY (id);

ALTER TABLE ONLY conectacausa.organization
    ADD CONSTRAINT organization_pkey PRIMARY KEY (id);

ALTER TABLE ONLY conectacausa.user_ability
    ADD CONSTRAINT user_ability_pkey PRIMARY KEY (user_id, ability_id);

ALTER TABLE ONLY conectacausa.user_opportunity
    ADD CONSTRAINT user_opportunity_pkey PRIMARY KEY (user_id, opportunity_id);

ALTER TABLE ONLY conectacausa."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);

ALTER TABLE ONLY conectacausa.zip_code
    ADD CONSTRAINT zip_code_pkey PRIMARY KEY (zip_code);

CREATE INDEX fki_a ON conectacausa.opportunity_ability USING btree (ability_id);

CREATE INDEX fki_idx ON conectacausa.opportunity USING btree (cause_type_id);

CREATE INDEX fki_organization_id_ ON conectacausa.opportunity USING btree (organization_id);

CREATE INDEX fki_q ON conectacausa.opportunity_ability USING btree (opportunity_id);

ALTER TABLE ONLY conectacausa.opportunity_ability
    ADD CONSTRAINT opportunity_ability_ability_id_fkey FOREIGN KEY (ability_id) REFERENCES conectacausa.ability(id) MATCH FULL NOT VALID;

ALTER TABLE ONLY conectacausa.opportunity_ability
    ADD CONSTRAINT opportunity_ability_opportunity_id_fkey FOREIGN KEY (opportunity_id) REFERENCES conectacausa.opportunity(id) MATCH FULL NOT VALID;

ALTER TABLE ONLY conectacausa.opportunity
    ADD CONSTRAINT opportunity_cause_type_id_fkey FOREIGN KEY (cause_type_id) REFERENCES conectacausa.cause_type(id) MATCH FULL NOT VALID;

ALTER TABLE ONLY conectacausa.opportunity
    ADD CONSTRAINT opportunity_organization_id_fkey FOREIGN KEY (organization_id) REFERENCES conectacausa.organization(id) MATCH FULL NOT VALID;

ALTER TABLE ONLY conectacausa.organization
    ADD CONSTRAINT organization_zip_code_fkey FOREIGN KEY (zip_code) REFERENCES conectacausa.zip_code(zip_code) MATCH FULL NOT VALID;

ALTER TABLE ONLY conectacausa.user_ability
    ADD CONSTRAINT user_ability_ability_id_fkey FOREIGN KEY (ability_id) REFERENCES conectacausa.ability(id) MATCH FULL NOT VALID;

ALTER TABLE ONLY conectacausa.user_ability
    ADD CONSTRAINT user_ability_user_id_fkey FOREIGN KEY (user_id) REFERENCES conectacausa."user"(id) MATCH FULL NOT VALID;

ALTER TABLE ONLY conectacausa.user_opportunity
    ADD CONSTRAINT user_opportunity_opportunity_id_fkey FOREIGN KEY (opportunity_id) REFERENCES conectacausa.opportunity(id) MATCH FULL NOT VALID;

ALTER TABLE ONLY conectacausa.user_opportunity
    ADD CONSTRAINT user_opportunity_user_id_fkey FOREIGN KEY (user_id) REFERENCES conectacausa."user"(id) MATCH FULL NOT VALID;

ALTER TABLE ONLY conectacausa."user"
    ADD CONSTRAINT user_zip_code_fkey FOREIGN KEY (zip_code) REFERENCES conectacausa.zip_code(zip_code) MATCH FULL NOT VALID;