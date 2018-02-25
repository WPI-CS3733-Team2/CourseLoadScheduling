--get sections from search in courses table
SELECT sections.*
FROM sections
WHERE course_id IN
	(
		SELECT courses.id
		FROM courses
		WHERE UPPER(courses.name) LIKE UPPER('%'||:searchTerm||'%')
			OR courses.number LIKE '%'||:searchTerm||'%'
	)
ORDER BY sections.id ASC;
