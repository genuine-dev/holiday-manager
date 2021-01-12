DROP TABLE IF EXISTS "user".account CASCADE;

CREATE TABLE "user".account
(
    user_id integer NOT NULL,
    account_id character varying(100) NOT NULL,
    password character varying(100) NOT NULL,
    admin_flg boolean NOT NULL,
    register_datetime timestamp DEFAULT now(),
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_account PRIMARY KEY (user_id),
    CONSTRAINT fk_account_user FOREIGN KEY (user_id)
        REFERENCES "user".user (id) MATCH SIMPLE
)
;
