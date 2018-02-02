--Search schedules by course
SELECT faculty.*
FROM faculty
WHERE id IN
(
	SELECT schedule.faculty_id
	FROM schedule
	WHERE id IN
	(	
		SELECT sections.schedule_id
		FROM sections
		WHERE course_id = :courseId
	)
)
ORDER BY faculty.id ASC;


