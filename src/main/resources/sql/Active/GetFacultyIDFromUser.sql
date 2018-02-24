--Get Faculty id
SELECT faculty.*
FROM faculty
WHERE id IN
(
	SELECT user_faculty_association.user_id
	FROM user_faculty_association
	WHERE id IN
	(
			SELECT users.id
			FROM users
			WHERE users.id = :usersId	
	)
)
ORDER BY faculty.id ASC;