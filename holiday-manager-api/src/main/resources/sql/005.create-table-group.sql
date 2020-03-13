DROP TABLE IF EXISTS "user".group CASCADE;

CREATE TABLE "user".group
(
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    register_datetime timestamp DEFAULT now(),
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_event_store PRIMARY KEY (id)
)
;