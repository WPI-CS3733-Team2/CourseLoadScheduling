--Get schedule from partial search in courses table
SELECT schedule.*
FROM schedule
WHERE id IN
(
	SELECT sections.schedule_id
	FROM sections
	WHERE calendar_id IN
	(
			SELECT calendar.id
			FROM calendar
			WHERE UPPER(calendar.semester) LIKE UPPER('%'||:searchTerm||'%')
	)
)
ORDER BY schedule.id ASC;