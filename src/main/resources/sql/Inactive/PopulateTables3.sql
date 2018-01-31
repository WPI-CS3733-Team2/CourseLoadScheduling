----------POPULATE ASSOCIATION---------------
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (57,1);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (58,2);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (1,3);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (63,4);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (30,5);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (34,6);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (28,6);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (8,8);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (22,9);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (33,10);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (31,11);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (2,12);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (29,13);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (14,14);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (17,15);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (32,16);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (59,17);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (23,18);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (60,19);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (3,20);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (50,21);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (7,22);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (55,23);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (61,24);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (39,25);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (37,26);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (53,27);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (15,28);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (18,29);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (6,30);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (5,31);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (12,32);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (9,33);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (16,34);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (38,35);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (27,36);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (26,37);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (62,38);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (13,39);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (40,40);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (21,41);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (25,42);
INSERT INTO course_load_association(faculty_id, course_load_id) VALUES (56,43);

----------------POPULATE COURSES ---------------

INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('1004', 'INTRO TO PROGRAMMING FOR NON-MAJORS', 4);

INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('1101', 'INTRO TO PROGRAM DESIGN', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('2011', 'INTRO TO MACHINE ORGANIZATION AND ASSEMBLY', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('2022', 'DISCRETE MATHEMATICS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('2102', 'OBJECT-ORIENTED DESIGN CONCEPTS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('2119', 'APPLICATION BUILDING WITH OBJECT-ORIENTED CONCEPTS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('2223', 'ALGORITHMS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('2301', 'SYSTEMS PROGRAMMING FOR NON-MAJORS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('2303', 'SYSTEMS PROGRAMMING CONCEPTS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('3013', 'OPERATING SYSTEMS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('3041', 'HUMAN-COMPUTER INTERATION', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('3043', 'SOCIAL IMPLICATIONS OF INFORMATION PROCESSING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('3133', 'FOUNDATIONS OF COMPUTER SCIENCE', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('3431', 'DATABASE SYSTEMS I', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('3733', 'SOFTWARE ENGINEERING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4032', 'NUMERIC METHODS FOR LINEAR AND NONLINEAR SYSTEMS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4120', 'ANALYSIS OF ALGORITHMS', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4341', 'INTRO TO ARTIFICIAL INTELLIGENCE', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4401', 'SOFTWARE SECURITY ENGINEERING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4432', 'DATABASE SYSTEMS II', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4513', 'DISTRIBUTED COMPUTER SYSTEMS', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4518', 'MOBILE AND UBIQUITOUS COMPUTING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4536', 'PROGRAMMING LANGUAGES', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('453X', 'MACHINE LEARNING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4731', 'COMPUTER GRAPHICS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4802', 'BIOVISUALIZATION', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('5003', 'FOUNDATIONS OF COMPUTER SCIENCE: AN INTRO', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('5007', 'INTRODUCTION TO PROGRAMMING CONCEPTS, DATA STRUCTURES, AND ALGORITMS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('503', 'FOUNDATIONS OF COMPUTER SCIENCE', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('509', 'DESIGN OF SOFTWARE SYSTEMS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('525', 'SPECIAL TOPICS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('534', 'ARTIFICIAL INTELLIGENCE', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('539', 'MACHINE LEARNING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('542', 'DATABASE MANAGEMENT SYSTEMS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('543', 'COMPUTER GRAPHICS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('548', 'KNOWLEDGE DISCOVERY AND DATA MINING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('565', 'USER MODELING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('571', 'CASE STUDIES IN COMPUTER SECURITY', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('573', 'DATA VISUALIZATION', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('582', 'BIOVISUALIZATION', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('585', 'BIG DATA MANAGEMENT', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('586', 'BIG DATA ANALYTICS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('210X', 'ACCELERATED OBJECT-ORIENTED DESIGN CONCEPTS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('403X', 'MOBILE AND UBIQUITOUS COMPUTING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('1102', 'ACCELERATED INTRODUCTION TO PROGRAM DESIGN', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('2103', 'ACCELERATED OBJECT-ORIENTED DESIGN CONCEPTS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('3516', 'COMPUTER NETWORKS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4033', 'NUMERICAL METHODS FOR CALCULUS AND DIFFERENTIAL EQUATIONS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4100', 'ARTIFICIAL INTELLIGENCE FOR INTERACTIVE MEDIA AND GAMES', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4123', 'THEORY OF COMPUTATION', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4233', 'OBJECT-ORIENTED ANALYSIS AND DESIGN', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4241', 'WEBWARE: COMPUTATIONAL TECHNOLOGY FOR NETWORK INFORMATION SYSTEMS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4404', 'TOOLS AND TECHNIQUES IN COMPUTER NETWORK SECURITY', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4445', 'DATA MINING AND KNOWLEDGE DISCOVERY IN DATABASES', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4515', 'COMPUTER ARCHITECTURE', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4516', 'ADVANCED COMPUTER NETWORKS', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4533', 'TECHNIQUES OF PROGRAMMING LANGUAGE TRANSLATION', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4732', 'COMPUTER ANIMATION', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4801', 'INTRODUCTION TO CRYPTOGRAPHY AND COMMUNICATION SECURITY', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('4803', 'BIOLOGICAL AND BIOMEDICAL DATABASE MINING', .5);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('502', 'OPERATING SYSTEMS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('504', 'ANALYSIS OF COMPUTATIONS AND SYSTEMS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('513', 'COMPUTER NETWORKS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('514', 'ADVANCED SYSTEMS ARCHITECTURE', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('521', 'LOGIC IN COMPUTER SCIENCE', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('522', 'NUMERICAL METHODS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('526', 'HUMAN-ROBOT INTERATION', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('528', 'MOBILE AND UBIQUITOUS COMPUTING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('529', 'MULTIMEDIA NETWORKING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('530', 'HIGH-PERFORMANCE NETWORKS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('533', 'MODELING AND PERFORMANCE EVALUATION OF NETWORK AND COMPUTER SYSTEMS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('535', 'ADVANCED TOPICS IN OPERATING SYSTEMS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('536', 'PROGRAMMING LANGUAGE DESIGN', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('540', 'ARTIFICIAL INTELLIGENCE IN DESIGN', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('544', 'COMPILER CONSTRUCTION', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('545', 'DIGITAL IMAGE PROCESSING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('546', 'HUMAN-COMPUTER INTERACTION', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('549', 'COMPUTER VISION', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('557', 'SOFTWARE SECURITY DESIGN AND ANALYSIS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('558', 'COMPUTER NETWORK SECURITY', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('561', 'ADVANCED TOPICS IN DATABASE SYSTEMS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('562', 'ADVANCES TOPICS IN SOFTWARE ENGINEERING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('564', 'ADVANCED TOPICS IN COMPUTER SECURITY', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('566', 'GRAPHICAL MODELS FOR REASONING UNDER UNCERTAINTY', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('567', 'EMPIRICAL MODELS FOR HUMAN-CENTERED COMPUTING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('568', 'ARTIFICIAL INTELLIGENCE FOR ADAPTIVE EDUCATIONAL TECHNOLOGY', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('577', 'ADVANCED COMPUTER AND COMMUNICATIONS NETWORKS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('578', 'CRYPTOGRAPHY AND DATA SECURITY', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('583', 'BIOLOGICAL AND BIOMEDICAL DATABASE MINING', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('584', 'ALGORITHMS: DESIGN AND ANALYSIS', 4);
    
INSERT INTO public.courses(
	 "number", name, frequency)
	VALUES ('5084', 'INTRODUCTION TO ALGORITHMS: DESIGN AND ANALYSIS', 4);

----------------POPULATE CALENDAR ---------------

INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'M', '10:00', '10:50');

INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'M', '11:00', '11:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'M', '12:00', '12:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'S', 'M', '6:00', '8:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'T', '11:00', '11:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'T', '12:00', '12:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'T', '1:00', '1:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'T', '3:00', '3:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'W', '8:00', '8:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'W', '9:00', '9:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'W', '10:00', '10:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'W', '11:00', '11:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'W', '12:00', '12:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'W', '1:00', '1:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'W', '1:00', '2:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'W', '2:00', '2:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'W', '3:00', '3:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'W', '4:00', '4:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'MW', '4:00', '5:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'MR', '11:00', '12:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'MR', '12:00', '1:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'MR', '2:00', '3:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'TF', '9:00', '10:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'TF', '12:00', '1:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'MTRF', '9:00', '9:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'MTRF', '10:00', '10:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'MTRF', '12:00', '12:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'MTRF', '2:00', '2:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'MTRF', '3:00', '3:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'C', 'MTRF', '4:00', '4:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'W', '8:00', '8:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'W', '9:00', '9:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'W', '10:00', '10:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'W', '11:00', '11:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'W', '12:00', '12:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'W', '12:00', '1:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'W', '1:00', '1:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'W', '2:00', '2:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'W', '3:00', '3:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'W', '3:00', '4:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'W', '4:00', '4:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'MR', '11:00', '12:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'MR', '2:00', '3:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'TF', '4:00', '5:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'MTRF', '9:00', '9:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'MTRF', '10:00', '10:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'MTRF', '12:00', '12:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'MTRF', '1:00', '1:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'MTRF', '2:00', '2:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'D', 'MTRF', '3:00', '3:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'S', 'W', '6:00', '8:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'S', 'R', '6:00', '8:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'S', 'MW', '12:00', '1:20');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'S', 'MW', '1:00', '2:20');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'S', 'MW', '4:00', '5:20');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'S', 'MW', '4:00', '5:30');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'S', 'MW', '4:00', '5:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'S', 'MR', '10:00', '11:50');
    
INSERT INTO public.calendar(
	 year, semester, days, start_time, end_time)
	VALUES (2018, 'S', 'TR', '4:00', '5:20');

----------------POPULATE SCHEDULES ---------------

INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (1, 'Schedule for Craig Wills');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (2, 'Schedule for Emmanuel Agu');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (3, 'Schedule for Joseph Beck');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (4, 'Schedule for David Brown');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (5, 'Schedule for Michael Ciaraldi');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (6, 'Schedule for Mark Claypool');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (7, 'Schedule for Joshua Cuneo');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (8, 'Schedule for Daniel Dougherty');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (9, 'Schedule for Mohamed Eltabakh');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (10, 'Schedule for Loris Fichera');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (11, 'Schedule for Kathryn Fisler');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (12, 'Schedule for Michael Gennert');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (13, 'Schedule for Tian Guo');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (14, 'Schedule for Glynis Hamel');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (15, 'Schedule for Lane Harrison');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (16, 'Schedule for Neil Heffernan');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (17, 'Schedule for George Heineman');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (18, 'Schedule for Micha Hofri');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (20, 'Schedule for Robert Kinicki');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (21, 'Schedule for Xiangnan Kong');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (22, 'Schedule for Dmitry Korkin');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (23, 'Schedule for Hugh Lauer');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (24, 'Schedule for Kyumin Lee');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (25, 'Schedule for Yanhua Lee');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (26, 'Schedule for Suzanne Mello-Stark');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (27, 'Schedule for Rodica Neamtu');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (28, 'Schedule for Carlo Pinciroli');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (29, 'Schedule for Gary Pollice');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (30, 'Schedule for Carolina Ruiz');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (31, 'Schedule for Elke Rundensteiner');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (32, 'Schedule for Gabor Sarkozy');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (33, 'Schedule for Douglas Selent');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (34, 'Schedule for Craig Shue');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (35, 'Schedule for Candace Sidner');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (36, 'Schedule for Gillian Smith');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (37, 'Schedule for Krishna Venkatasubramanian');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (38, 'Schedule for Robert Wall');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (39, 'Schedule for Jacob Whitehill');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (40, 'Schedule for Wilson Wong');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (41, 'Schedule for Jing Xiao');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (42, 'Schedule for Nicole Caligiuri');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (43, 'Schedule for Refie Cane');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (44, 'Schedule for Christine Caron');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (45, 'Schedule for Tricia Desmerais');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (46, 'Schedule for John Leveillee');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (47, 'Schedule for David Magid');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (48, 'Schedule for Michael Voorhis');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (49, 'Schedule for Ivon Arroyo');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (50, 'Schedule for Jerry Breecher');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (51, 'Schedule for Thomas Gannon');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (52, 'Schedule for Karen Lemone');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (53, 'Schedule for Keith Pray');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (54, 'Schedule for Stanley Selkow');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (55, 'Schedule for Jitendra Singh');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (56, 'Schedule for Aparna Mahadev');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (57, 'Schedule for Ahmedul Kabir');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (58, 'Schedule for Blake Nelson');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (59, 'Schedule for Hao Loi');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (60, 'Schedule for Herman Servatius');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (61, 'Schedule for Jorge Toro');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (62, 'Schedule for Susan Landau');
    
INSERT INTO public.schedule(
	faculty_id, schedule_name)
	VALUES (63, 'Schedule for Chun-Kit Ngan');
