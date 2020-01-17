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