--Get sections in schedule

SELECT courses.*
FROM courses
WHERE id IN
(
	SELECT sections.course_id
	FROM sections
	WHERE schedule_id IN
	(
		SELECT id 
		FROM schedule
		WHERE id = :scheduleId
	)
)
ORDER BY courses.id ASC;