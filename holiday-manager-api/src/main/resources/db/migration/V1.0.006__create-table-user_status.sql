DROP TABLE IF EXISTS "user".user_status CASCADE;

CREATE TABLE "user".user_status
(
    status character varying(100) NOT NULL,
    CONSTRAINT pk_user_status PRIMARY KEY (status)
)
;
