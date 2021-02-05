DROP TABLE IF EXISTS event.dispatcher_latest_event CASCADE;

CREATE TABLE event.dispatcher_latest_event (
	id varchar(255) NOT NULL,
	event_id bigint NOT NULL,
	primary key (id)
);

