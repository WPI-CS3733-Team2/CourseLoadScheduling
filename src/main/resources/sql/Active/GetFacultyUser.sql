--Get Faculty user using faculty number
SELECT users.*
FROM users
WHERE id IN
(
	SELECT user_faculty_association.user_id
	FROM user_faculty_association
	WHERE user_faculty_association.faculty_id = :facultyId
)
ORDER BY users.id ASC;