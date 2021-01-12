DROP TABLE IF EXISTS "user"."user" CASCADE;

CREATE TABLE "user"."user"
(
    id integer NOT NULL,
    status character varying(100) NOT NULL,
    email character varying(100) NOT NULL,
    name character varying(100) NOT NULL,
    hire_date date NOT NULL,
    register_datetime timestamp DEFAULT now(),
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_user PRIMARY KEY (id),
    CONSTRAINT fk_user_user_status FOREIGN KEY (status)
        REFERENCES "user".user_status (status) MATCH SIMPLE
)
;
