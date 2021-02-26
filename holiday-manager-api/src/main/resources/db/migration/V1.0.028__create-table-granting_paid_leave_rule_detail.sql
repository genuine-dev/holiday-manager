DROP TABLE IF EXISTS "user"."granting_paid_leave_rule_detail" CASCADE;

CREATE TABLE "user"."granting_paid_leave_rule_detail"
(
    id serial NOT NULL,
    rule_id integer NOT NULL,
    elapsed_year integer NOT NULL,
    elapsed_month integer NOT NULL,
    elapsed_days integer NOT NULL,
    grant_days real NOT NULL,
    created_at timestamp NOT NULL DEFAULT now(),
    updated_at timestamp NOT NULL DEFAULT now(),
    CONSTRAINT pk_granting_paid_leave_rule_detail PRIMARY KEY (id),
    CONSTRAINT fk_granting_paid_leave_rule_detail_rule_id FOREIGN KEY (rule_id)
    	 REFERENCES "user".granting_paid_leave_rule (id) MATCH SIMPLE
)
;
