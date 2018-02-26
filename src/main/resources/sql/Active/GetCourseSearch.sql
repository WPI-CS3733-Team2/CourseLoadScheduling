--get courses from what they are LIKE
SELECT courses.*
FROM courses
			WHERE UPPER(courses.name) LIKE UPPER('%'||:searchTerm||'%')
			OR UPPER(courses.number) LIKE UPPER('%'||:searchTerm||'%')

ORDER BY courses.id ASC;
