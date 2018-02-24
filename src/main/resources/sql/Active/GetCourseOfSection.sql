--Get courses of a schedule

SELECT courses.*
FROM courses
WHERE id IN
(
	SELECT sections.course_id
	FROM sections
	WHERE id = :id
)
ORDER BY courses.id ASC;