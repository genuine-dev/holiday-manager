DROP TABLE IF EXISTS "user"."user_rule" CASCADE;

CREATE TABLE "user"."user_rule"
(
    user_id integer NOT NULL,
    rule_id integer NOT NULL,
    CONSTRAINT pk_user_rule PRIMARY KEY (user_id),
    CONSTRAINT fk_user_rule_user_id FOREIGN KEY (user_id)
    	REFERENCES "user"."user" (id) MATCH SIMPLE,
    CONSTRAINT fk_user_rule_rule_id FOREIGN KEY (rule_id)
    	 REFERENCES "user".granting_paid_leave_rule (id) MATCH SIMPLE
)
;
