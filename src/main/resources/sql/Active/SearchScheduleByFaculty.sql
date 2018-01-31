--Search schedules by faculty

SELECT schedule.*
FROM schedule
WHERE faculty_id IN
(
	SELECT id 
	FROM faculty
	WHERE id = :facultyId
)
ORDER BY schedule.id ASC;
