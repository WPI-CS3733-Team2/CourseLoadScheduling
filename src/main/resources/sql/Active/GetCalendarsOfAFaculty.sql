--get calendars included in a schedule:
SELECT calendar.* 
FROM calendar
WHERE id IN
(
	SELECT sections.calendar_id
	FROM sections
	WHERE schedule_id IN
	(
		SELECT schedule.id
		FROM schedule
		WHERE faculty_id = :facultyId
	)
	
)
ORDER BY calendar.id ASC;
