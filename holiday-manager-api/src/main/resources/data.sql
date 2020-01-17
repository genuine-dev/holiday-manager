INSERT INTO customers (first_name, last_name) VALUES('Nobita', 'Nobi');
INSERT INTO customers (first_name, last_name) VALUES('Takeshi', 'Goda');
INSERT INTO customers (first_name, last_name) VALUES('Suneo', 'Honekawa');
INSERT INTO customers (first_name, last_name) VALUES('Shizuka', 'Minamoto');

--【001.create-schema-event.sql】
DROP SCHEMA IF EXISTS event CASCADE;
CREATE SCHEMA event 

--【002.create-table-event_store.sql】
--DROP SEQUENCE IF EXISTS contract.original_file_download_started_id_seq CASCADE;

--CREATE SEQUENCE contract.original_file_download_started_id_seq;

DROP TABLE IF EXISTS event.event_store CASCADE;

CREATE TABLE event.event_store
(
    id uuid NOT NULL,
    aggregate_id uuid NOT NULL,
    version integer NOT NULL,
    payload text NOT NULL,
    register_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_event_store PRIMARY KEY (id),
--    CONSTRAINT fk_original_file_download_started_contract_started FOREIGN KEY (contract_id)
--        REFERENCES contract.contract_started (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
)
;

--【003.create-table-snap_shot.sql】
--DROP SEQUENCE IF EXISTS contract.original_file_download_started_id_seq CASCADE;

--CREATE SEQUENCE contract.original_file_download_started_id_seq;

DROP TABLE IF EXISTS event.event_store CASCADE;

CREATE TABLE event.snap_shot
(
    aggregate_id uuid NOT NULL,
    version integer NOT NULL,
    payload text NOT NULL,
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_event_store PRIMARY KEY (aggregate_id),
--    CONSTRAINT fk_original_file_download_started_contract_started FOREIGN KEY (contract_id)
--        REFERENCES contract.contract_started (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
)
;

--【004.create-schema-user.sql】
DROP SCHEMA IF EXISTS user CASCADE;
CREATE SCHEMA user 
--【005.create-table-group.sql】
--DROP SEQUENCE IF EXISTS contract.original_file_download_started_id_seq CASCADE;

--CREATE SEQUENCE contract.original_file_download_started_id_seq;

DROP TABLE IF EXISTS event.event_store CASCADE;

CREATE TABLE user.group
(
    id integer NOT NULL,
    name character varying() NOT NULL,
    payload text NOT NULL,
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_event_store PRIMARY KEY (aggregate_id),
--    CONSTRAINT fk_original_file_download_started_contract_started FOREIGN KEY (contract_id)
--        REFERENCES contract.contract_started (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
)
;
--【006.create-table-manager.sql】
--DROP SEQUENCE IF EXISTS contract.original_file_download_started_id_seq CASCADE;

--CREATE SEQUENCE contract.original_file_download_started_id_seq;

DROP TABLE IF EXISTS user.manager CASCADE;

CREATE TABLE user.manager
(
    group_id integer NOT NULL,
    user_id integer NOT NULL,
    register_datetime timestamp DEFAULT now(),
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_group PRIMARY KEY (id),
--    CONSTRAINT fk_original_file_download_started_contract_started FOREIGN KEY (contract_id)
--        REFERENCES contract.contract_started (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
)
;
--【006.create-table-menber.sql】
--DROP SEQUENCE IF EXISTS contract.original_file_download_started_id_seq CASCADE;

--CREATE SEQUENCE contract.original_file_download_started_id_seq;

DROP TABLE IF EXISTS user.member CASCADE;

CREATE TABLE user.member
(
    group_id integer NOT NULL,
    user_id integer NOT NULL,
    register_datetime timestamp DEFAULT now(),
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_group PRIMARY KEY (id),
--    CONSTRAINT fk_original_file_download_started_contract_started FOREIGN KEY (contract_id)
--        REFERENCES contract.contract_started (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
)
;
--【006.create-table-user.sql】
--DROP SEQUENCE IF EXISTS contract.original_file_download_started_id_seq CASCADE;

--CREATE SEQUENCE contract.original_file_download_started_id_seq;

DROP TABLE IF EXISTS user.user CASCADE;

CREATE TABLE user.user
(
    id integer NOT NULL,
    status character varying() NOT NULL,
    email character varying() NOT NULL,
    name character varying() NOT NULL,
    hire_date date NOT NULL,
    register_datetime timestamp DEFAULT now(),
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_group PRIMARY KEY (id),
--    CONSTRAINT fk_original_file_download_started_contract_started FOREIGN KEY (contract_id)
--        REFERENCES contract.contract_started (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
)
;
--【007.create-table-user_status.sql】
--DROP SEQUENCE IF EXISTS contract.original_file_download_started_id_seq CASCADE;

--CREATE SEQUENCE contract.original_file_download_started_id_seq;

DROP TABLE IF EXISTS user.user_status CASCADE;

CREATE TABLE user.user_status
(
    status character varying() NOT NULL
    CONSTRAINT pk_group PRIMARY KEY (id),
--    CONSTRAINT fk_original_file_download_started_contract_started FOREIGN KEY (contract_id)
--        REFERENCES contract.contract_started (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
)
;
--【008.create-table-leave_period.sql】
--DROP SEQUENCE IF EXISTS contract.original_file_download_started_id_seq CASCADE;

--CREATE SEQUENCE contract.original_file_download_started_id_seq;

DROP TABLE IF EXISTS user.leave_period CASCADE;

CREATE TABLE user.leave_period
(
    id integer NOT NULL,
    user_id integer NOT NULL,
    from date NOT NULL,
    to date NOT NULL,
    register_datetime timestamp DEFAULT now(),
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_group PRIMARY KEY (id),
--    CONSTRAINT fk_original_file_download_started_contract_started FOREIGN KEY (contract_id)
--        REFERENCES contract.contract_started (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
)
;
--【009.create-table-account.sql】
--DROP SEQUENCE IF EXISTS contract.original_file_download_started_id_seq CASCADE;

--CREATE SEQUENCE contract.original_file_download_started_id_seq;

DROP TABLE IF EXISTS user.account CASCADE;

CREATE TABLE user.account
(
    user_id integer NOT NULL,
    account_id character varying() NOT NULL,
    password character varying() NOT NULL,
    admin_flg boolean NOT NULL,
    register_datetime timestamp DEFAULT now(),
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_account PRIMARY KEY (user_id),
--    CONSTRAINT fk_original_file_download_started_contract_started FOREIGN KEY (contract_id)
--        REFERENCES contract.contract_started (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
)
;
--【010.create-schema-paid_holidays_grant_rule.sql】
DROP SCHEMA IF EXISTS paid_holidays_grant_rule CASCADE;
CREATE SCHEMA paid_holidays_grant_rule 
--【011.create-table-paid_holidays_grant_rule.sql】
--DROP SEQUENCE IF EXISTS contract.original_file_download_started_id_seq CASCADE;

--CREATE SEQUENCE contract.original_file_download_started_id_seq;

DROP TABLE IF EXISTS paid_holidays_grant_rule.paid_holidays_grant_rule_detail CASCADE;

CREATE TABLE paid_holidays_grant_rule.paid_holidays_grant_rule_detail
(
    id integer NOT NULL,
    rule_id integer NOT NULL,
    elapsed_months month NOT NULL,
    granted_days integer NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (user_id),
--    CONSTRAINT fk_original_file_download_started_contract_started FOREIGN KEY (contract_id)
--        REFERENCES contract.contract_started (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
)
;
--【012.create-table-paid_holidays_grant_rule_detail.sql】
--DROP SEQUENCE IF EXISTS contract.original_file_download_started_id_seq CASCADE;

--CREATE SEQUENCE contract.original_file_download_started_id_seq;

DROP TABLE IF EXISTS paid_holidays_grant_rule.paid_holidays_grant_rule CASCADE;

CREATE TABLE paid_holidays_grant_rule.paid_holidays_grant_rule
(
    id integer NOT NULL,
    valid_from timestamp DEFAULT now(),
    valid_to timestamp DEFAULT now(),
    CONSTRAINT pk_account PRIMARY KEY (user_id),
--    CONSTRAINT fk_original_file_download_started_contract_started FOREIGN KEY (contract_id)
--        REFERENCES contract.contract_started (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
)
;