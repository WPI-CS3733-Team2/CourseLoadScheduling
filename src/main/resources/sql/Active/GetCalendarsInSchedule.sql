--get calendars included in a schedule:
SELECT calendar.* 
FROM calendar
WHERE id IN
(
	SELECT sections.calendar_id
	FROM sections
	WHERE schedule_id = :scheduleId
)
ORDER BY calendar.id ASC;
