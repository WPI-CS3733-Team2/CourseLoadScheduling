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
			WHERE UPPER(users.first_name) LIKE UPPER('%'||:searchTerm||'%')
				OR UPPER(users.last_name) LIKE UPPER('%'||:searchTerm||'%')
				OR UPPER(users.user_name) LIKE UPPER('%'||:searchTerm||'%')
	)
)
ORDER BY faculty.id ASC;