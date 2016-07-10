CREATE SEQUENCE user_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
    
CREATE SEQUENCE candidate_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
    
CREATE SEQUENCE company_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE candidate_skills_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE company_skills_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;
 
CREATE TABLE users (
 username VARCHAR(60) NOT NULL,
 password VARCHAR(60) NOT NULL,
 enabled SMALLINT NOT NULL DEFAULT 1,
 PRIMARY KEY (username)
 );
 
CREATE TABLE user_roles(
user_role_id numeric DEFAULT nextval('user_id_seq'::regclass) NOT NULL,
username VARCHAR(60) NOT NULL,
role VARCHAR(60) NOT NULL,
PRIMARY KEY (user_role_id),
UNIQUE (username, role),
CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES public.users (username)
);
 
CREATE TABLE candidates(
candidate_id numeric DEFAULT nextval('candidate_id_seq'::regclass) NOT NULL,
username VARCHAR(60) NOT NULL,
email VARCHAR(60) NOT NULL,
first_name VARCHAR(60) NOT NULL,
last_name VARCHAR(60) NOT NULL,
phone_number VARCHAR(16) NOT NULL,
PRIMARY KEY (candidate_id),
UNIQUE (username, email),
CONSTRAINT fk_candidate_username FOREIGN KEY (username) REFERENCES public.users (username)
);

CREATE TABLE companies(
company_id numeric DEFAULT nextval('company_id_seq'::regclass) NOT NULL,
username VARCHAR(60) NOT NULL,
email VARCHAR(60) NOT NULL,
company_name VARCHAR(60) NOT NULL,
phone_number VARCHAR(16) NOT NULL,
PRIMARY KEY (company_id),
UNIQUE (username, email),
CONSTRAINT fk_company_username FOREIGN KEY (username) REFERENCES public.users (username)
);

CREATE TABLE candidate_skills(
candidate_skill_id numeric DEFAULT nextval('candidate_skills_id_seq'::regclass) NOT NULL,
username VARCHAR(60) NOT NULL,
job_title VARCHAR(60),
years_experience VARCHAR(60),
location VARCHAR(60),
personal_skill character varying(250),
professional_skill character varying(250),
PRIMARY KEY (candidate_skill_id),
CONSTRAINT fk_skills_username FOREIGN KEY (username) REFERENCES public.users (username)
);
 
CREATE TABLE company_skills(
company_skill_id numeric DEFAULT nextval('company_skills_id_seq'::regclass) NOT NULL,
username VARCHAR(60) NOT NULL,
job_title VARCHAR(60),
years_experience VARCHAR(60),
location VARCHAR(60),
personal_skill character varying(250),
professional_skill character varying(250),
PRIMARY KEY (company_skill_id),
CONSTRAINT fk_company_skills_username FOREIGN KEY (username) REFERENCES public.users (username)
);

