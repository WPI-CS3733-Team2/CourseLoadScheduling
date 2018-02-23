--Get course section times:

SELECT calendar.*
FROM calendar
WHERE id IN
(
	SELECT sections.calendar_id 
	FROM sections
	WHERE id = :sectionId
)
ORDER BY calendar.id ASC;
