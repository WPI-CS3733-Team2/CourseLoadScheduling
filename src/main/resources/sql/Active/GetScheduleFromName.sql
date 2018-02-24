--Get schedule from partial search in its own table
SELECT schedule.*
FROM schedule
WHERE UPPER(schedule.schedule_name) LIKE UPPER('%'||:searchTerm||'%')

ORDER BY schedule.id ASC;