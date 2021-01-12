INSERT INTO "user".account
	(
		user_id,
		account_id,
		password,
		admin_flg
	)
SELECT
	id,
	'admin',
	'pass',
	true
FROM
	"user".user
WHERE
	email = 'admin@example.com'
;