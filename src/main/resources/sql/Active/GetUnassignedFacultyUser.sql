--Get users without Courses
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
			WHERE assigned = false	
	)
)
ORDER BY users.last_name, users.first_name ASC;