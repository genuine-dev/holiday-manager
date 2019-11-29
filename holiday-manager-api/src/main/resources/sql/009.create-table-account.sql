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
    CONSTRAINT pk_group PRIMARY KEY (id),
--    CONSTRAINT fk_original_file_download_started_contract_started FOREIGN KEY (contract_id)
--        REFERENCES contract.contract_started (id) MATCH SIMPLE
--        ON UPDATE NO ACTION
--        ON DELETE NO ACTION
)
;