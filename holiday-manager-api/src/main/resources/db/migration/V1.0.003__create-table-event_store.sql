DROP TABLE IF EXISTS event.event_store CASCADE;

CREATE TABLE event.event_store
(
    id uuid NOT NULL,
    aggregate_id uuid NOT NULL,
    version integer NOT NULL,
    payload text NOT NULL,
    register_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_event_store PRIMARY KEY (id),
    CONSTRAINT fk_event_store_snap_shot FOREIGN KEY (aggregate_id)
    REFERENCES event.snap_shot (aggregate_id) MATCH SIMPLE
)
;
