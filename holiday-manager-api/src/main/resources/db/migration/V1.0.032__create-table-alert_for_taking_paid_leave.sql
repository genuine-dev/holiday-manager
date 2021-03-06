DROP TABLE IF EXISTS "user".alert_for_taking_paid_leave CASCADE;

CREATE TABLE "user".alert_for_taking_paid_leave
(
    user_id integer NOT NULL,
    days numeric NOT NULL,
    deadline date NOT NULL,
    register_datetime timestamp DEFAULT now(),
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_alert_for_taking_paid_leave PRIMARY KEY (user_id),
    CONSTRAINT fk_alert_for_taking_paid_leave_user FOREIGN KEY (user_id)
        REFERENCES "user"."user" (id) MATCH SIMPLE
)
;
