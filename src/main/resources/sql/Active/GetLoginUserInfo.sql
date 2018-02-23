SELECT

-- users
users.id AS users_id,
users.wpi_id AS users_wpi_id,
users.user_name AS users_user_name,
users.first_name AS users_first_name,
users.last_name AS users_last_name,
users.email AS users_email,
users.encrypted_password AS users_encrypted_password,
users.salt AS users_salt,
users.account_state AS users_account_state,
users.created_at AS users_created_at,
users.updated_at AS users_updated_at,
users.deleted AS users_deleted,

--faculty
faculty.id AS faculty_id,

--user_roles
user_roles.id AS user_roles_id,
user_roles.role_name AS user_roles_role_name

FROM users
LEFT OUTER JOIN user_faculty_association ON users.id = user_faculty_association.user_id
LEFT OUTER JOIN faculty ON user_faculty_association.faculty_id = faculty.id
-- exacty one role for a user
INNER JOIN users_roles_links ON users.id = users_roles_links.user_id
INNER JOIN user_roles ON users_roles_links.role_id = user_roles.id
WHERE users.user_name = :userName
AND users.deleted = false;