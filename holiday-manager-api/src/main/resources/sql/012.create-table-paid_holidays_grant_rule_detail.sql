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