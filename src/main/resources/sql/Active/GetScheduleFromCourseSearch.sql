--Get schedule from partial search in courses table
SELECT schedule.*
FROM schedule
WHERE id IN
(
	SELECT sections.schedule_id
	FROM sections
	WHERE course_id IN
	(
			SELECT courses.id
			FROM courses
			WHERE UPPER(courses.name) LIKE UPPER('%'||:searchTerm||'%')
				OR courses.number LIKE '%'||:searchTerm||'%'
	)
)
ORDER BY schedule.id ASC;