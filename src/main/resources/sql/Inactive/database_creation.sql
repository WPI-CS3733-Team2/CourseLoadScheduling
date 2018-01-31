DROP TABLE users CASCADE;
DROP TABLE user_roles CASCADE;
DROP TABLE faculty_history;
DROP TABLE faculty_ranks;
DROP TABLE user_states CASCADE;
DROP TABLE users_roles_links;
CREATE TABLE users
(
	id serial PRIMARY KEY,
	wpi_id varchar(255) UNIQUE NOT NULL,
	user_name varchar(255) UNIQUE NOT NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255) UNIQUE NOT NULL,
	encrypted_password varchar(255) NOT NULL,
	salt varchar(255) UNIQUE NOT NULL,
	account_state varchar(255) NOT NULL,
	deleted boolean NOT NULL DEFAULT(FALSE),
	created_at timestamp without time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp without time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);


DROP TABLE users_history;
CREATE TABLE users_history
(
	id serial PRIMARY KEY,
	user_id integer NOT NULL REFERENCES users(id) ON DELETE CASCADE,
	wpi_id varchar(255) NOT NULL,
	user_name varchar(255) NOT NULL,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	encrypted_password varchar(255) NOT NULL,
	salt varchar(255) NOT NULL,
	account_state varchar(255) NOT NULL,
	deleted boolean NOT NULL DEFAULT(FALSE),
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);

--DROP FUNCTION insert_user_history();
CREATE FUNCTION insert_user_history() RETURNS TRIGGER AS
$BODY$
BEGIN
INSERT INTO users_history(user_id, wpi_id, user_name, first_name, last_name, email,encrypted_password, salt, account_state, deleted)
VALUES(OLD.id, OLD.wpi_id, OLD.user_name, OLD.first_name, OLD.last_name, OLD.email, OLD.encrypted_password, OLD.salt, OLD.account_state, OLD.deleted);
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
	

CREATE TRIGGER update_users
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE PROCEDURE insert_user_history();

DROP TABLE faculty;
CREATE TABLE faculty
(
	id serial PRIMARY KEY,
	--user_id integer NOT NULL REFERENCES users(id) ON DELETE CASCADE,
	rank integer NOT NULL,
	--schedule_id integer REFERENCES schedule(id),
	assigned boolean NOT NULL DEFAULT(FALSE),
	deleted boolean NOT NULL DEFAULT(FALSE)
);


--DROP TABLE user_faculty_association;
CREATE TABLE user_faculty_association
(
	id serial PRIMARY KEY,
	user_id varchar(255) UNIQUE NOT NULL REFERENCES users(wpi_id) ON DELETE CASCADE,
	faculty_id integer UNIQUE NOT NULL REFERENCES faculty(id) ON DELETE CASCADE
);


-----------------------------------------------------------------------------------------
--DROP TABLE calendar;
CREATE TABLE calendar(
	id serial PRIMARY KEY,
	year integer NOT NULL,
	semester varchar(255) NOT NULL,
	days varchar(255) NOT NULL,
	-- start_time integer NOT NULL,
	start_time varchar(255) NOT NULL,
	-- end_time integer NOT NULL
	end_time varchar(255) NOT NULL
	-- CHECK(start_time >= 0),
	-- CHECK(start_time < 23),
	-- CHECK(end_time >= 0),
	-- CHECK(end_time < 23)
);
--DROP TABLE calendar_history;
CREATE TABLE calendar_history(
	id serial PRIMARY KEY,
	calendar_id integer NOT NULL REFERENCES calendar(id) ON DELETE CASCADE,
	year integer NOT NULL,
	semester varchar(255) NOT NULL,
	days varchar(255) NOT NULL,
	start_time varchar(255) NOT NULL,
	end_time varchar(255) NOT NULL,
	-- start_time integer NOT NULL,
	-- end_time integer NOT NULL,
	created_at timestamp with time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);

--DROP FUNCTION insert_calendar_history();
CREATE FUNCTION insert_calendar_history() RETURNS TRIGGER AS
$BODY$
BEGIN
INSERT INTO calendar_history(calendar_id, year, semester, days, start_time, end_time)
VALUES(OLD.id, OLD.year, OLD.semester, OLD.days, OLD.start_time, OLD.end_time);
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
	

CREATE TRIGGER update_calendar
BEFORE UPDATE ON calendar
FOR EACH ROW
EXECUTE PROCEDURE insert_calendar_history();

--schedule
--DROP TABLE schedule;
CREATE TABLE schedule(
	id serial PRIMARY KEY,
	faculty_id integer NOT NULL REFERENCES faculty(id) ON DELETE CASCADE,
	schedule_name varchar(255) NOT NULL
);


--SECTIONS && COURSES--------------------------------------------------------------
-- DROP TABLE public.courses;
CREATE TABLE public.courses
(
    id serial PRIMARY KEY,
    "number" varchar(255) UNIQUE NOT NULL,
    name varchar(255) NOT NULL,
    frequency integer NOT NULL
    -- CONSTRAINT courses_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;


--Table: public.course_history
--DROP TABLE course_history;
CREATE TABLE public.course_history
(
	id serial PRIMARY KEY,
	course_id integer NOT NULL references courses(id) ON DELETE CASCADE,
	"number" varchar(255) NOT NULL,
	name varchar(255) NOT NULL,
	frequency integer NOT NULL,
	-- CONSTRAINT course_history_pkey PRIMARY KEY (id),
	created_at timestamp with time zone NOT NULL DEFAULT (CURRENT_TIMESTAMP)
);

--DROP FUNCTION insert_course_history();
CREATE FUNCTION insert_course_history() RETURNS TRIGGER AS
$BODY$
BEGIN
INSERT INTO course_history(course_id, "number", name, frequency)
VALUES(OLD.id, OLD."number", OLD.name, OLD.frequency);
RETURN NEW;
END;
$BODY$
LANGUAGE plpgsql VOLATILE;
	

CREATE TRIGGER update_course
BEFORE UPDATE ON courses
FOR EACH ROW
EXECUTE PROCEDURE insert_course_history();



--DROP TABLE sections;
CREATE TABLE public.sections
(
    id serial PRIMARY KEY,
    crn integer NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    type character varying COLLATE pg_catalog."default" NOT NULL,
    --term character varying COLLATE pg_catalog."default" NOT NULL,
    expected_population integer NOT NULL,
    course_id integer NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
    calendar_id integer NOT NULL REFERENCES calendar(id),
    schedule_id integer NULL,
    CONSTRAINT FK_schedule_id FOREIGN KEY (schedule_id) REFERENCES schedule(id)
    --CONSTRAINT sections_pkey PRIMARY KEY (id),
    -- CONSTRAINT sections_course_id_fkey FOREIGN KEY (course_id)
    --     REFERENCES public.courses (id) MATCH SIMPLE
    --     ON UPDATE NO ACTION
    --     ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

-----------------------------------------------------------------------------------
--DROP TABLE request_state;
CREATE TABLE request_state(
	id serial PRIMARY KEY,
	state varchar(255) UNIQUE NOT NULL
);

--DROP TABLE request_type;
CREATE TABLE request_type(
	id serial PRIMARY KEY,
	type varchar(255) UNIQUE NOT NULL
);

--DROP TABLE requests;
CREATE TABLE requests
(
    id serial PRIMARY KEY,
    faculty_id integer NOT NULL REFERENCES faculty(id),
    type integer NOT NULL REFERENCES request_type(id),
    state integer NOT NULL REFERENCES request_state(id),
	course integer NOT NULL REFERENCES courses(id),
	--request_term varchar(255) NOT NULL,
	section integer NOT NULL REFERENCES sections(id),
    data TEXT NOT NULL,
	created_at timestamp without time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP),
	updated_at timestamp without time zone NOT NULL DEFAULT(CURRENT_TIMESTAMP)
);


--------------------------------------------------------------------------------------
-- DROP TABLE course_load;
CREATE TABLE course_load
(
	id serial PRIMARY KEY,
	type varchar(255) NOT NULL DEFAULT'REGULAR',
	amount integer NOT NULL,
	deleted boolean NOT NULL DEFAULT(FALSE)
);

--DROP TABLE course_load_assosciation;
CREATE TABLE course_load_assosciation
(
	id serial PRIMARY KEY,
	faculty_id integer NOT NULL REFERENCES faculty(id) ON DELETE CASCADE,
	course_load_id integer NOT NULL REFERENCES course_load(id) ON DELETE CASCADE,
	deleted boolean NOT NULL DEFAULT(FALSE)
	--UNIQUE(user_id, role_id, deleted)
);



