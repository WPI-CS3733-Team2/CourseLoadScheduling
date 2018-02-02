--Search schedules by faculty

SELECT faculty.*
FROM faculty
WHERE id IN
(
	SELECT user_faculty_association.faculty_id
	FROM user_faculty_association
	WHERE user_id IN
	(
		SELECT users.id
		FROM users
		WHERE wpi_id = :wpiId
	)
)
ORDER BY user.id ASC;
