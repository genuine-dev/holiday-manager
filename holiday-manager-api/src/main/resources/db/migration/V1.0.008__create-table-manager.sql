DROP TABLE IF EXISTS "user".manager CASCADE;

CREATE TABLE "user".manager
(
    group_id integer NOT NULL,
    user_id integer NOT NULL,
    register_datetime timestamp DEFAULT now(),
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_manager PRIMARY KEY (group_id, user_id),
    CONSTRAINT fk_manager_group FOREIGN KEY (group_id)
        REFERENCES "user".group (id) MATCH SIMPLE,
    CONSTRAINT fk_manager_user FOREIGN KEY (user_id)
        REFERENCES "user".user (id) MATCH SIMPLE
)
;