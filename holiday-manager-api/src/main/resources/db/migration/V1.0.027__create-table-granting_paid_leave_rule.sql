DROP TABLE IF EXISTS "user"."granting_paid_leave_rule" CASCADE;

CREATE TABLE "user"."granting_paid_leave_rule"
(
    id serial NOT NULL,
    name character varying(255) NOT NULL,
    created_at timestamp NOT NULL DEFAULT now(),
    updated_at timestamp NOT NULL DEFAULT now(),
    CONSTRAINT pk_granting_paid_leave_rule PRIMARY KEY (id)
)
;
