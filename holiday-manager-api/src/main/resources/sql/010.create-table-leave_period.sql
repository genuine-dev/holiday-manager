DROP TABLE IF EXISTS "user".leave_period CASCADE;

CREATE TABLE "user".leave_period
(
    id integer NOT NULL,
    user_id integer NOT NULL,
    "from" date NOT NULL,
    "to" date NOT NULL,
    register_datetime timestamp DEFAULT now(),
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_leave_period PRIMARY KEY (id),
    CONSTRAINT fk_leave_period_user FOREIGN KEY (user_id)
        REFERENCES "user".user (id) MATCH SIMPLE
)
;
