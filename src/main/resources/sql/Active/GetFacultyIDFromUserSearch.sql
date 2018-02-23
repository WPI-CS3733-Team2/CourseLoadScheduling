--Get Faculty id from partial search in users table
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
			WHERE users.first_name LIKE '%'||:searchTerm||'%'
				OR users.last_name LIKE '%'||:searchTerm||'%'
				OR users.user_name LIKE '%'||:searchTerm||'%'
	)
)
ORDER BY faculty.id ASC;