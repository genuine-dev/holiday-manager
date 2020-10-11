DROP TABLE IF EXISTS event.snap_shot CASCADE;

CREATE TABLE event.snap_shot
(
    aggregate_id uuid NOT NULL,
    version integer NOT NULL,
    payload text NOT NULL,
    update_datetime timestamp DEFAULT now(),
    CONSTRAINT pk_snap_shot PRIMARY KEY (aggregate_id)
)
;

