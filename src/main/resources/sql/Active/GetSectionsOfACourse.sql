--Get course section times:


SELECT sections.calendar_id 
FROM sections
WHERE course_id = :courseId
	
ORDER BY sections.id ASC;
