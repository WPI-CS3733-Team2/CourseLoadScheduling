--get faculty:
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
		WHERE user_name = :userName)

)
ORDER BY faculty.id ASC;
