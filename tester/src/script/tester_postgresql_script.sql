-- SQL Manager Lite for PostgreSQL 6.1.2.53864
-- ---------------------------------------
-- Host      : localhost
-- Database  : webtester
-- Version   : PostgreSQL 9.5.16, compiled by Visual C++ build 1800, 32-bit

SET search_path = public, pg_catalog;
DROP INDEX IF EXISTS public.account_role_idx;
DROP SEQUENCE IF EXISTS public.sequence;
DROP SEQUENCE IF EXISTS public.test_seq;
DROP SEQUENCE IF EXISTS public.result_seq;
DROP SEQUENCE IF EXISTS public.question_seq;
DROP SEQUENCE IF EXISTS public.answer_seq;
DROP SEQUENCE IF EXISTS public.account_role_seq;
DROP SEQUENCE IF EXISTS public.account_seq;
DROP TABLE IF EXISTS public.account_registration;
DROP TABLE IF EXISTS public.result;
DROP TABLE IF EXISTS public.answer;
DROP TABLE IF EXISTS public.question;
DROP TABLE IF EXISTS public.test;
DROP TABLE IF EXISTS public.account_role;
DROP TABLE IF EXISTS public.account;
DROP TABLE IF EXISTS public.role;
SET check_function_bodies = false;
--
-- Structure for table role (OID = 32768) :
--
CREATE TABLE public.role (
    id bigint NOT NULL,
    name varchar(15) NOT NULL
)
WITH (oids = false);
--
-- Structure for table account (OID = 32773) :
--
CREATE TABLE public.account (
    id bigint NOT NULL,
    login varchar(30) NOT NULL,
    password varchar(255) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(100) NOT NULL,
    middle_name varchar(100),
    email varchar(50) NOT NULL,
    created timestamp(0) without time zone DEFAULT now() NOT NULL,
    active boolean DEFAULT false NOT NULL
)
WITH (oids = false);
--
-- Structure for table account_role (OID = 32787) :
--
CREATE TABLE public.account_role (
    id bigint NOT NULL,
    id_account bigint NOT NULL,
    id_role bigint NOT NULL
)
WITH (oids = false);
--
-- Structure for table test (OID = 32803) :
--
CREATE TABLE public.test (
    id bigint NOT NULL,
    id_account bigint,
    name varchar(255) NOT NULL,
    description text NOT NULL,
    duration_per_question smallint DEFAULT 30 NOT NULL,
    CONSTRAINT chk_duration CHECK ((duration_per_question >= 30))
)
WITH (oids = false);
--
-- Structure for table question (OID = 32813) :
--
CREATE TABLE public.question (
    id bigint NOT NULL,
    id_test bigint NOT NULL,
    name text NOT NULL
)
WITH (oids = false);
--
-- Structure for table answer (OID = 32831) :
--
CREATE TABLE public.answer (
    id_answer bigint NOT NULL,
    id_question bigint NOT NULL,
    name_answer text NOT NULL,
    correct boolean DEFAULT false NOT NULL
)
WITH (oids = false);
--
-- Structure for table result (OID = 32855) :
--
CREATE TABLE public.result (
    id bigint NOT NULL,
    id_account bigint NOT NULL,
    id_test bigint,
    percent double precision NOT NULL,
    test_name text NOT NULL,
    created date DEFAULT now() NOT NULL
)
WITH (oids = false);
--
-- Structure for table account_registration (OID = 32879) :
--
CREATE TABLE public.account_registration (
    id_account bigint NOT NULL,
    token varchar(255) NOT NULL
)
WITH (oids = false);
--
-- Definition for sequence account_seq (OID = 32889) :
--
CREATE SEQUENCE public.account_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence account_role_seq (OID = 32891) :
--
CREATE SEQUENCE public.account_role_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence answer_seq (OID = 32893) :
--
CREATE SEQUENCE public.answer_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence question_seq (OID = 32895) :
--
CREATE SEQUENCE public.question_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence result_seq (OID = 32897) :
--
CREATE SEQUENCE public.result_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence test_seq (OID = 32899) :
--
CREATE SEQUENCE public.test_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
--
-- Definition for sequence sequence (OID = 41026) :
--
CREATE SEQUENCE public.sequence
    START WITH 4
    INCREMENT BY 1
    MAXVALUE 200000
    NO MINVALUE
    CACHE 1;
--
-- Definition for index account_role_idx (OID = 32802) :
--
CREATE UNIQUE INDEX account_role_idx ON public.account_role USING btree (id_account, id_role);
--
-- Definition for index role_pkey (OID = 32771) :
--
ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey
    PRIMARY KEY (id);
--
-- Definition for index account_pkey (OID = 32781) :
--
ALTER TABLE ONLY account
    ADD CONSTRAINT account_pkey
    PRIMARY KEY (id);
--
-- Definition for index account_login_key (OID = 32783) :
--
ALTER TABLE ONLY account
    ADD CONSTRAINT account_login_key
    UNIQUE (login);
--
-- Definition for index account_email_key (OID = 32785) :
--
ALTER TABLE ONLY account
    ADD CONSTRAINT account_email_key
    UNIQUE (email);
--
-- Definition for index account_role_pkey (OID = 32790) :
--
ALTER TABLE ONLY account_role
    ADD CONSTRAINT account_role_pkey
    PRIMARY KEY (id);
--
-- Definition for index account_role_fk (OID = 32792) :
--
ALTER TABLE ONLY account_role
    ADD CONSTRAINT account_role_fk
    FOREIGN KEY (id_account) REFERENCES account(id) ON UPDATE CASCADE ON DELETE CASCADE;
--
-- Definition for index account_role_fk1 (OID = 32797) :
--
ALTER TABLE ONLY account_role
    ADD CONSTRAINT account_role_fk1
    FOREIGN KEY (id_role) REFERENCES role(id) ON UPDATE CASCADE ON DELETE RESTRICT;
--
-- Definition for index test_pkey (OID = 32810) :
--
ALTER TABLE ONLY test
    ADD CONSTRAINT test_pkey
    PRIMARY KEY (id);
--
-- Definition for index quastion_pkey (OID = 32819) :
--
ALTER TABLE ONLY question
    ADD CONSTRAINT quastion_pkey
    PRIMARY KEY (id);
--
-- Definition for index quastion_fk (OID = 32821) :
--
ALTER TABLE ONLY question
    ADD CONSTRAINT quastion_fk
    FOREIGN KEY (id_test) REFERENCES test(id) ON UPDATE CASCADE ON DELETE CASCADE;
--
-- Definition for index answer_pkey (OID = 32838) :
--
ALTER TABLE ONLY answer
    ADD CONSTRAINT answer_pkey
    PRIMARY KEY (id_answer);
--
-- Definition for index answer_fk (OID = 32840) :
--
ALTER TABLE ONLY answer
    ADD CONSTRAINT answer_fk
    FOREIGN KEY (id_question) REFERENCES question(id) ON UPDATE CASCADE ON DELETE CASCADE;
--
-- Definition for index test_fk (OID = 32850) :
--
ALTER TABLE ONLY test
    ADD CONSTRAINT test_fk
    FOREIGN KEY (id_account) REFERENCES account(id) ON UPDATE CASCADE ON DELETE SET NULL;
--
-- Definition for index result_pkey (OID = 32862) :
--
ALTER TABLE ONLY result
    ADD CONSTRAINT result_pkey
    PRIMARY KEY (id);
--
-- Definition for index result_fk (OID = 32869) :
--
ALTER TABLE ONLY result
    ADD CONSTRAINT result_fk
    FOREIGN KEY (id_account) REFERENCES account(id) ON UPDATE CASCADE ON DELETE CASCADE;
--
-- Definition for index result_fk1 (OID = 32874) :
--
ALTER TABLE ONLY result
    ADD CONSTRAINT result_fk1
    FOREIGN KEY (id_test) REFERENCES test(id) ON UPDATE CASCADE ON DELETE SET NULL;
--
-- Definition for index account_registration_pkey (OID = 32882) :
--
ALTER TABLE ONLY account_registration
    ADD CONSTRAINT account_registration_pkey
    PRIMARY KEY (id_account);
--
-- Definition for index account_registration_fk (OID = 32884) :
--
ALTER TABLE ONLY account_registration
    ADD CONSTRAINT account_registration_fk
    FOREIGN KEY (id_account) REFERENCES account(id) ON UPDATE CASCADE ON DELETE CASCADE;
--
-- Comments
--
COMMENT ON SCHEMA public IS 'standard public schema';
COMMENT ON COLUMN public.test.duration_per_question IS 'in seconds';

-- SQL Manager Lite for PostgreSQL 6.1.2.53864
-- ---------------------------------------
-- Host      : localhost
-- Database  : webtester
-- Version   : PostgreSQL 9.5.16, compiled by Visual C++ build 1800, 32-bit



SET search_path = public, pg_catalog;
--
-- Data for table public.role (OID = 32768) (LIMIT 0,4)
--
INSERT INTO role (id, name)
VALUES (1, 'student');

INSERT INTO role (id, name)
VALUES (2, 'tutor');

INSERT INTO role (id, name)
VALUES (3, 'administrator');

INSERT INTO role (id, name)
VALUES (4, 'advanced tutor');

--
-- Data for table public.account (OID = 32773) (LIMIT 0,18)
--


INSERT INTO account (id, login, password, first_name, last_name, middle_name, email, created, active)
VALUES (22, 'sofi', 'sofi', 'sofi', 'sofi', 'sofi', 'sofi', '2019-05-14 16:58:28', false);

INSERT INTO account (id, login, password, first_name, last_name, middle_name, email, created, active)
VALUES (1, 'admin', 'vasya', 'fan', 'ivan', 'wefewf', 'admin@mail.ru', '2019-05-17 20:14:34', false);

INSERT INTO account (id, login, password, first_name, last_name, middle_name, email, created, active)
VALUES (24, 'misha', 'misha', 'djehfhsegufhweifhweifjhwiehfui', 'misha', 'misha', 'misha', '2019-05-17 20:15:24', true);


--
-- Data for table public.account_role (OID = 32787) (LIMIT 0,28)
--
INSERT INTO account_role (id, id_account, id_role)
VALUES (102, 1, 3);

INSERT INTO account_role (id, id_account, id_role)
VALUES (103, 1, 2);

INSERT INTO account_role (id, id_account, id_role)
VALUES (104, 1, 4);

INSERT INTO account_role (id, id_account, id_role)
VALUES (105, 1, 1);

INSERT INTO account_role (id, id_account, id_role)
VALUES (107, 24, 2);

INSERT INTO account_role (id, id_account, id_role)
VALUES (108, 24, 3);

INSERT INTO account_role (id, id_account, id_role)
VALUES (109, 22, 3);

--
-- Data for table public.test (OID = 32803) (LIMIT 0,2)
--
INSERT INTO test (id, id_account, name, description, duration_per_question)
VALUES (2, 1, '?????????? ????', '???? ???? ??????? ?????????? ?? ??????? ?? ?????????', 60);

INSERT INTO test (id, id_account, name, description, duration_per_question)
VALUES (1, 1, 'At vero eos et accusamus et iusto odio dignissimos', 'Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. ', 50);

--
-- Data for table public.question (OID = 32813) (LIMIT 0,10)
--
INSERT INTO question (id, id_test, name)
VALUES (2, 1, '??? ???? ?????? ?????? ??? ???? ?');

INSERT INTO question (id, id_test, name)
VALUES (1, 1, '?????? ??? ?????????? ??????');

INSERT INTO question (id, id_test, name)
VALUES (3, 1, '??? ??? ???????');

INSERT INTO question (id, id_test, name)
VALUES (5, 1, '??????? ?????????? ???????');

INSERT INTO question (id, id_test, name)
VALUES (10, 1, '??????????? ???????');

INSERT INTO question (id, id_test, name)
VALUES (11, 1, '??? ????????? ???????');

INSERT INTO question (id, id_test, name)
VALUES (12, 1, '? ??? ???? ?????? ??? ?????????');

INSERT INTO question (id, id_test, name)
VALUES (13, 2, '????? ?? ?????? ?');

INSERT INTO question (id, id_test, name)
VALUES (4, 2, 'eeeeeeee');

INSERT INTO question (id, id_test, name)
VALUES (14, 2, '??????? ?????????? ???????');

--
-- Data for table public.answer (OID = 32831) (LIMIT 0,19)
--
INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (10, 4, 'Some answer', true);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (4, 2, '??????', false);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (5, 2, '????', false);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (9, 3, 'quis nostrum exercitationem', false);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (8, 3, 'ratione voluptatem sequi nesciunt', true);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (2, 1, '?? ?????? ?? ??? ??? ??????', true);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (7, 3, '??? ?? ??? ?? ???????', false);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (12, 2, '??', true);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (25, 10, '??', true);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (26, 10, '???', false);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (13, 5, '??', true);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (1, 1, '??? ??? ??? ?????? ?? ?????', false);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (27, 11, '??', true);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (28, 11, '???', false);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (29, 12, '??', true);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (30, 13, '? ?????', true);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (32, 14, 'yes', false);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (33, 14, 'no', true);

INSERT INTO answer (id_answer, id_question, name_answer, correct)
VALUES (34, 4, 'dededed', true);

--
-- Data for sequence public.account_seq (OID = 32889)
--
SELECT pg_catalog.setval('account_seq', 32, true);
--
-- Data for sequence public.account_role_seq (OID = 32891)
--
SELECT pg_catalog.setval('account_role_seq', 122, true);
--
-- Data for sequence public.answer_seq (OID = 32893)
--
SELECT pg_catalog.setval('answer_seq', 34, true);
--
-- Data for sequence public.question_seq (OID = 32895)
--
SELECT pg_catalog.setval('question_seq', 14, true);
--
-- Data for sequence public.result_seq (OID = 32897)
--
SELECT pg_catalog.setval('result_seq', 1, false);
--
-- Data for sequence public.test_seq (OID = 32899)
--
SELECT pg_catalog.setval('test_seq', 1, false);
--
-- Data for sequence public.sequence (OID = 41026)
--
SELECT pg_catalog.setval('sequence', 4, true);



