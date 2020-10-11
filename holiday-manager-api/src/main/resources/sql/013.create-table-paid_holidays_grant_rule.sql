DROP TABLE IF EXISTS paid_holidays_grant_rule.paid_holidays_grant_rule CASCADE;

CREATE TABLE paid_holidays_grant_rule.paid_holidays_grant_rule
(
    id integer NOT NULL,
    valid_from timestamp DEFAULT now(),
    valid_to timestamp DEFAULT now(),
    CONSTRAINT pk_paid_holidays_grant_rule PRIMARY KEY (id)
)
;
