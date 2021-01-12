INSERT INTO "user".user
	(
		id,
		status,
		email,
		name,
		hire_date,
		update_datetime
	)
SELECT
	nextval('user.user_id_seq'),
	'ACTIVE',
	'admin@example.com',
	'管理者',
	'2021-01-01',
	now()
;