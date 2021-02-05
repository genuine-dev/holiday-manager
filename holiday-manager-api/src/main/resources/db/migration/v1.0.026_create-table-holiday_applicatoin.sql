DROP TABLE IF EXISTS "user".holiday_applicatoin CASCADE;

create table "user".holiday_applicatoin (
	id varchar(36) NOT NULL,
	applicant_id integer NOT NULL,
	approver_id integer ,
	date timestamp NOT NULL,
	kind varchar(20) NOT NULL,
	status varchar(20) NOT NULL,
	type varchar(20) NOT NULL,
	created_at timestamp NOT NULL,
	updated_at timestamp NOT NULL,
	PRIMARY KEY (id)
);
