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