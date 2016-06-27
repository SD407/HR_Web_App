 CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 
CREATE TABLE users (
 username VARCHAR(64) NOT NULL,
 password VARCHAR(64) NOT NULL,
 enabled SMALLINT NOT NULL DEFAULT 1,
 PRIMARY KEY (username)
 );
 
CREATE TABLE user_roles(
user_role_id numeric DEFAULT nextval('user_id_seq'::regclass) NOT NULL,
username VARCHAR(64) NOT NULL,
role VARCHAR(64) NOT NULL,
PRIMARY KEY (user_role_id),
UNIQUE (username, role)
CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES public.users (username)
);
 
 