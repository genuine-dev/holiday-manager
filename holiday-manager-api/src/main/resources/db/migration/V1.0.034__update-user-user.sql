UPDATE
    "user".user
SET
    leftover_holiday = 0.0
WHERE
    leftover_holiday IS null
;
