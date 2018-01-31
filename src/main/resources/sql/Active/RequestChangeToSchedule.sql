--Request change to schedule (Query courses assigned to schedule)

SELECT courses.*
FROM courses
WHERE id IN
(
    SELECT course_id
    FROM sections
    WHERE schedule_id = :scheduleID
);
