--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: fit_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fit_user (
    id bigint NOT NULL,
    address character varying(150) NOT NULL,
    age character varying(3) NOT NULL,
    balance bigint,
    birthdate timestamp without time zone NOT NULL,
    email character varying(50) NOT NULL,
    gender character varying(6) NOT NULL,
    img_url character varying(100),
    name character varying(20) NOT NULL,
    passport character varying(16) NOT NULL,
    password character varying(120) NOT NULL,
    patronymic character varying(20) NOT NULL,
    surname character varying(20) NOT NULL,
    telephone character varying(20) NOT NULL
);


ALTER TABLE public.fit_user OWNER TO postgres;

--
-- Name: fit_user_group_training; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fit_user_group_training (
    fit_user_id bigint NOT NULL,
    group_training_id bigint NOT NULL
);


ALTER TABLE public.fit_user_group_training OWNER TO postgres;

--
-- Name: fit_user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.fit_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fit_user_id_seq OWNER TO postgres;

--
-- Name: fit_user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.fit_user_id_seq OWNED BY public.fit_user.id;


--
-- Name: fit_user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.fit_user_roles (
    fit_user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.fit_user_roles OWNER TO postgres;

--
-- Name: group_training; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.group_training (
    id bigint NOT NULL,
    coach_id bigint NOT NULL,
    end_date timestamp without time zone NOT NULL,
    start_date timestamp without time zone NOT NULL,
    status character varying(10) NOT NULL,
    training_id bigint NOT NULL
);


ALTER TABLE public.group_training OWNER TO postgres;

--
-- Name: group_training_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.group_training_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.group_training_id_seq OWNER TO postgres;

--
-- Name: group_training_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.group_training_id_seq OWNED BY public.group_training.id;


--
-- Name: news; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.news (
    id bigint NOT NULL,
    content character varying(1500) NOT NULL,
    date_time timestamp without time zone NOT NULL,
    header character varying(50) NOT NULL,
    img_url character varying(100)
);


ALTER TABLE public.news OWNER TO postgres;

--
-- Name: news_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.news_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.news_id_seq OWNER TO postgres;

--
-- Name: news_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.news_id_seq OWNED BY public.news.id;


--
-- Name: personal_training; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.personal_training (
    id bigint NOT NULL,
    coach_id bigint NOT NULL,
    end_date timestamp without time zone NOT NULL,
    start_date timestamp without time zone NOT NULL,
    status character varying(10) NOT NULL,
    fit_user_id bigint
);


ALTER TABLE public.personal_training OWNER TO postgres;

--
-- Name: personal_training_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.personal_training_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.personal_training_id_seq OWNER TO postgres;

--
-- Name: personal_training_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.personal_training_id_seq OWNED BY public.personal_training.id;


--
-- Name: refresh_token; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.refresh_token (
    id bigint NOT NULL,
    expiry_date timestamp without time zone NOT NULL,
    token character varying(200) NOT NULL,
    fit_user_id bigint NOT NULL
);


ALTER TABLE public.refresh_token OWNER TO postgres;

--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    name character varying(20) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- Name: subscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subscription (
    id bigint NOT NULL,
    begin_date timestamp without time zone NOT NULL,
    contract_number character varying(15) NOT NULL,
    end_date timestamp without time zone NOT NULL,
    subscription_type_id bigint NOT NULL
);


ALTER TABLE public.subscription OWNER TO postgres;

--
-- Name: subscription_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.subscription_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.subscription_id_seq OWNER TO postgres;

--
-- Name: subscription_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subscription_id_seq OWNED BY public.subscription.id;


--
-- Name: subscription_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.subscription_type (
    id bigint NOT NULL,
    cost character varying(5) NOT NULL,
    name character varying(200) NOT NULL,
    placement_time character varying(10) NOT NULL,
    validity integer NOT NULL
);


ALTER TABLE public.subscription_type OWNER TO postgres;

--
-- Name: subscription_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.subscription_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.subscription_type_id_seq OWNER TO postgres;

--
-- Name: subscription_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.subscription_type_id_seq OWNED BY public.subscription_type.id;


--
-- Name: training; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.training (
    id bigint NOT NULL,
    name character varying(50) NOT NULL
);


ALTER TABLE public.training OWNER TO postgres;

--
-- Name: training_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.training_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.training_id_seq OWNER TO postgres;

--
-- Name: training_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.training_id_seq OWNED BY public.training.id;


--
-- Name: fit_user id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fit_user ALTER COLUMN id SET DEFAULT nextval('public.fit_user_id_seq'::regclass);


--
-- Name: group_training id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_training ALTER COLUMN id SET DEFAULT nextval('public.group_training_id_seq'::regclass);


--
-- Name: news id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news ALTER COLUMN id SET DEFAULT nextval('public.news_id_seq'::regclass);


--
-- Name: personal_training id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personal_training ALTER COLUMN id SET DEFAULT nextval('public.personal_training_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- Name: subscription id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription ALTER COLUMN id SET DEFAULT nextval('public.subscription_id_seq'::regclass);


--
-- Name: subscription_type id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription_type ALTER COLUMN id SET DEFAULT nextval('public.subscription_type_id_seq'::regclass);


--
-- Name: training id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.training ALTER COLUMN id SET DEFAULT nextval('public.training_id_seq'::regclass);


--
-- Data for Name: fit_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fit_user (id, address, age, balance, birthdate, email, gender, img_url, name, passport, password, patronymic, surname, telephone) FROM stdin;
1	Москва, ул. Энгельса, н. 3 п.23	50	2500	1971-02-03 00:00:00	AdminProfile@gmail.com	M	admin.jpg	Валерий	345793459	$2a$10$FM7DRHjUIfhmAXgsq1uKC.tmah/oymXYr1xNKCLgKMOVPCI20rJdy	Александрович	Подкорытов	88005553535
2	Санкт-Петербург, Ленина ул., д. 3 кв.17	43	0	1978-02-01 00:00:00	UserProfile@gmail.com	M	user.jpg	Владимир	4723637495	$2a$10$bJK2ha21uzpVRWxDAevZDusyxZT9gsr1SeJalDFEF0wl2TpnQKQg2	Петрович	Столяров	89236003343
3	г Санкт-Петербург, Большой Сампсониевский пр-кт, д 7	35	200	1994-06-01 00:00:00	CoachProfile@gmail.com	M	coach.jpg	Гавриил	2313333342	$2a$10$SdgV.8KAG/Jvh2Xd8x2aqOfy5XRrTlyDx4mafbH9xfb2ubN9e.AKK	Иванович	Цуканов	89219692138
\.


--
-- Data for Name: fit_user_group_training; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fit_user_group_training (fit_user_id, group_training_id) FROM stdin;
\.


--
-- Data for Name: fit_user_roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.fit_user_roles (fit_user_id, role_id) FROM stdin;
1	1
1	2
2	1
3	1
3	3
\.


--
-- Data for Name: group_training; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.group_training (id, coach_id, end_date, start_date, status, training_id) FROM stdin;
\.


--
-- Data for Name: news; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.news (id, content, date_time, header, img_url) FROM stdin;
1	Современные технологии достигли такого уровня, что семантический разбор внешних противодействий позволяет оценить значение переосмысления внешнеэкономических политик. Прежде всего, повышение уровня гражданского сознания предоставляет широкие возможности для инновационных методов управления процессами. Как уже неоднократно упомянуто, явные признаки победы институционализации объявлены нарушающими общечеловеческие нормы этики и морали.	2021-11-22 00:00:00	Современные технологии	\N
2	Кстати, базовые сценарии поведения пользователей будут смешаны с не уникальными данными до степени совершенной неузнаваемости, из-за чего возрастает их статус бесполезности. В целом, конечно, семантический разбор внешних противодействий представляет собой интересный эксперимент проверки анализа существующих паттернов поведения. Господа, курс на социально-ориентированный национальный проект говорит о возможностях форм воздействия.	2021-10-02 00:00:00	Cценарии	fitness-club.jpg
3	Значимость этих проблем настолько очевидна, что понимание сути ресурсосберегающих технологий создаёт предпосылки для глубокомысленных рассуждений. Предварительные выводы неутешительны: перспективное планирование однозначно фиксирует необходимость новых принципов формирования материально-технической и кадровой базы. Идейные соображения высшего порядка, а также выбранный нами инновационный путь влечет за собой процесс внедрения и модернизации как самодостаточных, так и внешне зависимых концептуальных решений.	2020-05-16 00:00:00	Значимость	group-training.jpg
4	Сегодня нашему фитнес клубу исполняется год. Ровно год назад мы открыли наше заведение. С тех пор мы неоднакратно отличались наилучшим сервисом и качеством залов, что оставляло множество положительных эмоций у наших клиентов.	2021-12-01 00:00:00	День рождение	swimming-pool.jpg
5	В выходные наш фитнес клуб работаь не будет!	2021-11-02 00:00:00	Планы на выходные	running.jpg
6	20 декабря с 10:00 по 14:00 будут проводиться технические работы на нашем сайте.	2021-11-16 00:00:00	Технические работы	\N
7	Найдена денежная сумма в размере 453 тысячи рублей. Находка передана на пост охраны перед входом в фитнес клуб. Охранник делиться суммой не намерен, так что хозяину данной находки придется отбирать деньги силой.	2021-08-11 00:00:00	Найдены деньги	fitness-equipment.jpg
8	Значимость этих проблем настолько очевидна, что понимание сути ресурсосберегающих технологий создаёт предпосылки для глубокомысленных рассуждений. Предварительные выводы неутешительны: перспективное планирование однозначно фиксирует необходимость новых принципов формирования материально-технической и кадровой базы. Идейные соображения высшего порядка, а также выбранный нами инновационный путь влечет за собой процесс внедрения и модернизации как самодостаточных, так и внешне зависимых концептуальных решений.	2020-05-16 00:00:00	Значимость	group-training.jpg
9	Сегодня нашему фитнес клубу исполняется год. Ровно год назад мы открыли наше заведение. С тех пор мы неоднакратно отличались наилучшим сервисом и качеством залов, что оставляло множество положительных эмоций у наших клиентов.	2021-12-01 00:00:00	День рождение	swimming-pool.jpg
10	В выходные наш фитнес клуб работаь не будет!	2021-11-02 00:00:00	Планы на выходные	running.jpg
11	20 декабря с 10:00 по 14:00 будут проводиться технические работы на нашем сайте.	2021-11-16 00:00:00	Технические работы	\N
12	Найдена денежная сумма в размере 453 тысячи рублей. Находка передана на пост охраны перед входом в фитнес клуб. Охранник делиться суммой не намерен, так что хозяину данной находки придется отбирать деньги силой.	2021-08-11 00:00:00	Найдены деньги	fitness-equipment.jpg
13	Значимость этих проблем настолько очевидна, что понимание сути ресурсосберегающих технологий создаёт предпосылки для глубокомысленных рассуждений. Предварительные выводы неутешительны: перспективное планирование однозначно фиксирует необходимость новых принципов формирования материально-технической и кадровой базы. Идейные соображения высшего порядка, а также выбранный нами инновационный путь влечет за собой процесс внедрения и модернизации как самодостаточных, так и внешне зависимых концептуальных решений.	2020-05-16 00:00:00	Значимость	exercise-equipment.jpg
14	Сегодня нашему фитнес клубу исполняется год. Ровно год назад мы открыли наше заведение. С тех пор мы неоднакратно отличались наилучшим сервисом и качеством залов, что оставляло множество положительных эмоций у наших клиентов.	2021-12-01 00:00:00	День рождение	swimming-pool.jpg
15	В выходные наш фитнес клуб работаь не будет!	2021-11-02 00:00:00	Планы на выходные	running.jpg
16	20 декабря с 10:00 по 14:00 будут проводиться технические работы на нашем сайте.	2021-11-16 00:00:00	Технические работы	personal-training
17	Найдена денежная сумма в размере 453 тысячи рублей. Находка передана на пост охраны перед входом в фитнес клуб. Охранник делиться суммой не намерен, так что хозяину данной находки придется отбирать деньги силой.	2021-08-11 00:00:00	Найдены деньги	\N
\.


--
-- Data for Name: personal_training; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.personal_training (id, coach_id, end_date, start_date, status, fit_user_id) FROM stdin;
\.


--
-- Data for Name: refresh_token; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.refresh_token (id, expiry_date, token, fit_user_id) FROM stdin;
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, name) FROM stdin;
1	ROLE_USER
2	ROLE_MODERATOR
3	ROLE_COACH
\.


--
-- Data for Name: subscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subscription (id, begin_date, contract_number, end_date, subscription_type_id) FROM stdin;
1	2021-11-26 00:00:00	89824655645	2021-11-27 00:00:00	1
2	2021-12-31 00:00:00	89824561232	2022-01-01 00:00:00	2
3	2021-11-26 00:00:00	89824655645	2021-11-27 00:00:00	3
\.


--
-- Data for Name: subscription_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.subscription_type (id, cost, name, placement_time, validity) FROM stdin;
1	3900	Серебряный	ALL_DAY	6
2	5900	Золотой	ALL_DAY	12
3	4900	Бронзовый	EVENING	1
\.


--
-- Data for Name: training; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.training (id, name) FROM stdin;
1	Кардио
2	Бокс
3	Аэробика
\.


--
-- Name: fit_user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.fit_user_id_seq', 3, true);


--
-- Name: group_training_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.group_training_id_seq', 1, false);


--
-- Name: news_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.news_id_seq', 17, true);


--
-- Name: personal_training_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.personal_training_id_seq', 1, false);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 3, true);


--
-- Name: subscription_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subscription_id_seq', 3, true);


--
-- Name: subscription_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.subscription_type_id_seq', 3, true);


--
-- Name: training_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.training_id_seq', 3, true);


--
-- Name: fit_user fit_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fit_user
    ADD CONSTRAINT fit_user_pkey PRIMARY KEY (id);


--
-- Name: group_training group_training_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_training
    ADD CONSTRAINT group_training_pkey PRIMARY KEY (id);


--
-- Name: news news_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.news
    ADD CONSTRAINT news_pkey PRIMARY KEY (id);


--
-- Name: personal_training personal_training_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personal_training
    ADD CONSTRAINT personal_training_pkey PRIMARY KEY (id);


--
-- Name: refresh_token refresh_token_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT refresh_token_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: subscription subscription_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT subscription_pkey PRIMARY KEY (id);


--
-- Name: subscription_type subscription_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription_type
    ADD CONSTRAINT subscription_type_pkey PRIMARY KEY (id);


--
-- Name: training training_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.training
    ADD CONSTRAINT training_pkey PRIMARY KEY (id);


--
-- Name: refresh_token uk_4r29ii8k1qkijd98days7s1ce; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT uk_4r29ii8k1qkijd98days7s1ce UNIQUE (fit_user_id);


--
-- Name: role uk_8sewwnpamngi6b1dwaa88askk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT uk_8sewwnpamngi6b1dwaa88askk UNIQUE (name);


--
-- Name: fit_user uk_mldipv6rt0rx385vyykv70grd; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fit_user
    ADD CONSTRAINT uk_mldipv6rt0rx385vyykv70grd UNIQUE (email);


--
-- Name: training uk_qd6qkbaxm0qb0baf3jqqpmpd3; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.training
    ADD CONSTRAINT uk_qd6qkbaxm0qb0baf3jqqpmpd3 UNIQUE (name);


--
-- Name: refresh_token uk_r4k4edos30bx9neoq81mdvwph; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT uk_r4k4edos30bx9neoq81mdvwph UNIQUE (token);


--
-- Name: fit_user fk540dvmw73m0bbrdoq7c02t5x6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fit_user
    ADD CONSTRAINT fk540dvmw73m0bbrdoq7c02t5x6 FOREIGN KEY (id) REFERENCES public.subscription(id);


--
-- Name: subscription fk8mltpw5ur3yf5nw2d7t4rnxc6; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.subscription
    ADD CONSTRAINT fk8mltpw5ur3yf5nw2d7t4rnxc6 FOREIGN KEY (subscription_type_id) REFERENCES public.subscription_type(id);


--
-- Name: group_training fkag6mpur39ppgijgdq7xcvu49w; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.group_training
    ADD CONSTRAINT fkag6mpur39ppgijgdq7xcvu49w FOREIGN KEY (training_id) REFERENCES public.training(id);


--
-- Name: refresh_token fki1j3lcrrs6mfi3g63d4tajj7g; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.refresh_token
    ADD CONSTRAINT fki1j3lcrrs6mfi3g63d4tajj7g FOREIGN KEY (fit_user_id) REFERENCES public.fit_user(id);


--
-- Name: fit_user_roles fkinwl9bj4fgnwv4r2pxm4wq11c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fit_user_roles
    ADD CONSTRAINT fkinwl9bj4fgnwv4r2pxm4wq11c FOREIGN KEY (fit_user_id) REFERENCES public.fit_user(id);


--
-- Name: fit_user_roles fkk1qvn6lrmnux3my2f68x3d7pw; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fit_user_roles
    ADD CONSTRAINT fkk1qvn6lrmnux3my2f68x3d7pw FOREIGN KEY (role_id) REFERENCES public.role(id);


--
-- Name: fit_user_group_training fklwpni69hq0pofs6e4jq22sqy7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fit_user_group_training
    ADD CONSTRAINT fklwpni69hq0pofs6e4jq22sqy7 FOREIGN KEY (fit_user_id) REFERENCES public.fit_user(id);


--
-- Name: personal_training fkt7xjlmhqeyw7lbgqwnkhq4a2c; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.personal_training
    ADD CONSTRAINT fkt7xjlmhqeyw7lbgqwnkhq4a2c FOREIGN KEY (fit_user_id) REFERENCES public.fit_user(id);


--
-- Name: fit_user_group_training fktf280y75278h5ohcmpl0vmy30; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.fit_user_group_training
    ADD CONSTRAINT fktf280y75278h5ohcmpl0vmy30 FOREIGN KEY (group_training_id) REFERENCES public.group_training(id);


--
-- PostgreSQL database dump complete
--

