--Get sections in schedule

SELECT sections.*
FROM sections
WHERE schedule_id IN
(
	SELECT id 
	FROM schedule
	WHERE id = :scheduleId
)
ORDER BY sections.course_id ASC;
