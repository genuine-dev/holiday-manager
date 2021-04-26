DROP TABLE IF EXISTS "user".paid_leave_grant_history CASCADE;

CREATE TABLE "user".paid_leave_grant_history
(
    user_id integer NOT NULL,
    rule_detail_id integer NOT NULL,
    created_at timestamp DEFAULT now(),
    CONSTRAINT pk_paid_leave_grant_history PRIMARY KEY (user_id, rule_detail_id),
    CONSTRAINT fk_apaid_leave_grant_history_user FOREIGN KEY (user_id)
        REFERENCES "user"."user" (id) MATCH SIMPLE,
    CONSTRAINT fk_apaid_leave_grant_history_rule FOREIGN KEY (rule_detail_id)
        REFERENCES "user"."granting_paid_leave_rule_detail" (id) MATCH SIMPLE
)
;
