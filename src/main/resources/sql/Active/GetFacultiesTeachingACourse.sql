--Search schedules by course
SELECT schedule.faculty_id
FROM schedule
WHERE id IN
(	
	SELECT sections.schedule_id
	FROM sections
	WHERE course_id = :courseId
)
ORDER BY schedule.id ASC;


