DROP TABLE IF EXISTS paid_holidays_grant_rule.paid_holidays_grant_rule_detail CASCADE;

CREATE TABLE paid_holidays_grant_rule.paid_holidays_grant_rule_detail
(
    id integer NOT NULL,
    rule_id integer NOT NULL,
    elapsed_months date NOT NULL,
    granted_days integer NOT NULL,
    CONSTRAINT pk_paid_holidays_grant_rule_detail PRIMARY KEY (id),
    CONSTRAINT fk_paid_holidays_grant_rule_detail_rule FOREIGN KEY (rule_id)
        REFERENCES paid_holidays_grant_rule.paid_holidays_grant_rule (id) MATCH SIMPLE
)
;
