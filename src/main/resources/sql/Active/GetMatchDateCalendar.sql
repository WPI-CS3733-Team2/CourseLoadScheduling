SELECT *
FROM calendar
WHERE year = :year 
AND semester = :semester
AND days = :days
AND start_time = :start_time
AND end_time = :end_time

ORDER BY calendar.id ASC;