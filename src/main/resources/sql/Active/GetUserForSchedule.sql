--Get a user associated with a schedule
SELECT users.*
FROM users
WHERE id IN
(
	SELECT user_faculty_association.user_id
	FROM user_faculty_association
	WHERE id IN
	(
			SELECT faculty.id
			FROM faculty
			WHERE faculty.id IN
			(
				SELECT schedule.faculty_id
				FROM schedule
				WHERE schedule.id = :scheduleId
			)
	)
	
)
ORDER BY users.last_name, users.first_name ASC;


