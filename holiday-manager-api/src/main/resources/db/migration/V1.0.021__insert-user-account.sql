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
	'$2a$10$WHjHECVs1dJKJYSLq.yfi.ETr0plkJASzCdiKTEU45G56CH832Rb6',
	true
FROM
	"user".user
WHERE
	email = 'admin@example.com'
;