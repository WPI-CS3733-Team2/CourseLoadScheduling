--Search schedules by faculty

SELECT schedule.*
FROM schedule
WHERE faculty_id IN
(
	SELECT id 
	FROM faculty
	WHERE id = :facultyID
)
ORDER BY schedule.id ASC;
