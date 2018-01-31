--get course section times:
SELECT calendar.*
FROM calendar
WHERE id IN
(
	SELECT sections.calendar_id 
	FROM sections
	WHERE course_id = :courseId
)
ORDER BY calendar.id ASC;
