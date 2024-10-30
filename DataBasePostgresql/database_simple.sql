--
-- PostgreSQL database dump
--

-- Dumped from database version 12.20 (Ubuntu 12.20-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 16.4

-- Started on 2024-10-30 09:05:27

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

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16708)
-- Name: session; Type: TABLE; Schema: public; Owner: template-app
--

CREATE TABLE public.session (
    session_id integer NOT NULL,
    token character varying(50) NOT NULL,
    "user" character varying(100) NOT NULL,
    "isNotDelete" boolean DEFAULT false NOT NULL,
    date_update timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    remote_ip character varying(100)
);


ALTER TABLE public.session OWNER TO "template-app";

--
-- TOC entry 203 (class 1259 OID 16713)
-- Name: d_session_session_id_seq; Type: SEQUENCE; Schema: public; Owner: template-app
--

CREATE SEQUENCE public.d_session_session_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.d_session_session_id_seq OWNER TO "template-app";

--
-- TOC entry 2991 (class 0 OID 0)
-- Dependencies: 203
-- Name: d_session_session_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: template-app
--

ALTER SEQUENCE public.d_session_session_id_seq OWNED BY public.session.session_id;


--
-- TOC entry 204 (class 1259 OID 16715)
-- Name: setting; Type: TABLE; Schema: public; Owner: template-app
--

CREATE TABLE public.setting (
    setting_id integer NOT NULL,
    name character varying(100) NOT NULL,
    value character varying(100),
    description character varying(200)
);


ALTER TABLE public.setting OWNER TO "template-app";

--
-- TOC entry 205 (class 1259 OID 16718)
-- Name: d_setting_setting_id_seq; Type: SEQUENCE; Schema: public; Owner: template-app
--

CREATE SEQUENCE public.d_setting_setting_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.d_setting_setting_id_seq OWNER TO "template-app";

--
-- TOC entry 2992 (class 0 OID 0)
-- Dependencies: 205
-- Name: d_setting_setting_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: template-app
--

ALTER SEQUENCE public.d_setting_setting_id_seq OWNED BY public.setting.setting_id;


--
-- TOC entry 206 (class 1259 OID 16720)
-- Name: roles; Type: TABLE; Schema: public; Owner: template-app
--

CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(100) NOT NULL
);


ALTER TABLE public.roles OWNER TO "template-app";

--
-- TOC entry 207 (class 1259 OID 16723)
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: template-app
--

CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.roles_id_seq OWNER TO "template-app";

--
-- TOC entry 2993 (class 0 OID 0)
-- Dependencies: 207
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: template-app
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- TOC entry 208 (class 1259 OID 16725)
-- Name: users; Type: TABLE; Schema: public; Owner: template-app
--

CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(100) NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    comments character varying(255),
    email character varying(255) DEFAULT NULL::character varying,
    first_name character varying(255) DEFAULT NULL::character varying,
    last_name character varying(255) DEFAULT NULL::character varying,
    last_login time without time zone
);


ALTER TABLE public.users OWNER TO "template-app";

--
-- TOC entry 209 (class 1259 OID 16736)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: template-app
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_id_seq OWNER TO "template-app";

--
-- TOC entry 2994 (class 0 OID 0)
-- Dependencies: 209
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: template-app
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 210 (class 1259 OID 16738)
-- Name: users_roles; Type: TABLE; Schema: public; Owner: template-app
--

CREATE TABLE public.users_roles (
    id integer NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);


ALTER TABLE public.users_roles OWNER TO "template-app";

--
-- TOC entry 211 (class 1259 OID 16741)
-- Name: users_roles_id_seq; Type: SEQUENCE; Schema: public; Owner: template-app
--

CREATE SEQUENCE public.users_roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.users_roles_id_seq OWNER TO "template-app";

--
-- TOC entry 2995 (class 0 OID 0)
-- Dependencies: 211
-- Name: users_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: template-app
--

ALTER SEQUENCE public.users_roles_id_seq OWNED BY public.users_roles.id;


--
-- TOC entry 2823 (class 2604 OID 16743)
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- TOC entry 2819 (class 2604 OID 16744)
-- Name: session session_id; Type: DEFAULT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.session ALTER COLUMN session_id SET DEFAULT nextval('public.d_session_session_id_seq'::regclass);


--
-- TOC entry 2822 (class 2604 OID 16745)
-- Name: setting setting_id; Type: DEFAULT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.setting ALTER COLUMN setting_id SET DEFAULT nextval('public.d_setting_setting_id_seq'::regclass);


--
-- TOC entry 2824 (class 2604 OID 16746)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 2830 (class 2604 OID 16747)
-- Name: users_roles id; Type: DEFAULT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.users_roles ALTER COLUMN id SET DEFAULT nextval('public.users_roles_id_seq'::regclass);


--
-- TOC entry 2979 (class 0 OID 16720)
-- Dependencies: 206
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: template-app
--

COPY public.roles (id, name) FROM stdin;
1	ROLE_ADMIN
2	ROLE_DEVELOPER
3	ROLE_USER
\.


--
-- TOC entry 2975 (class 0 OID 16708)
-- Dependencies: 202
-- Data for Name: session; Type: TABLE DATA; Schema: public; Owner: template-app
--

COPY public.session (session_id, token, "user", "isNotDelete", date_update, remote_ip) FROM stdin;
\.


--
-- TOC entry 2977 (class 0 OID 16715)
-- Dependencies: 204
-- Data for Name: setting; Type: TABLE DATA; Schema: public; Owner: template-app
--

COPY public.setting (setting_id, name, value, description) FROM stdin;
1	ip_access	93.182.24.0/24; 195.9.240.0/24; 192.168.0.0/16	Список подсетей которые имеют доступ к сервису
\.


--
-- TOC entry 2981 (class 0 OID 16725)
-- Dependencies: 208
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: template-app
--

COPY public.users (id, username, password, enabled, date, comments, email, first_name, last_name, last_login) FROM stdin;
1	admin	$2a$12$DtYgscRVdYCoFhj3kyQhsO.qzpTEA0WJ2n.K9TsxWqyi0b0r4wXhG	t	2022-10-26 11:44:44.885616	\N	\N	\N	\N	\N
2	user	$2a$12$vH5QPsw.poUXM.qabtLskOBsUf42tKlsBv5Yl0eEOtsx6Udr.LRKu	t	2022-10-26 11:44:44.885616	\N	\N	\N	\N	\N
\.


--
-- TOC entry 2983 (class 0 OID 16738)
-- Dependencies: 210
-- Data for Name: users_roles; Type: TABLE DATA; Schema: public; Owner: template-app
--

COPY public.users_roles (id, user_id, role_id) FROM stdin;
1	1	1
2	1	2
3	2	3
\.


--
-- TOC entry 2996 (class 0 OID 0)
-- Dependencies: 203
-- Name: d_session_session_id_seq; Type: SEQUENCE SET; Schema: public; Owner: template-app
--

SELECT pg_catalog.setval('public.d_session_session_id_seq', 18, true);


--
-- TOC entry 2997 (class 0 OID 0)
-- Dependencies: 205
-- Name: d_setting_setting_id_seq; Type: SEQUENCE SET; Schema: public; Owner: template-app
--

SELECT pg_catalog.setval('public.d_setting_setting_id_seq', 1, true);


--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 207
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: template-app
--

SELECT pg_catalog.setval('public.roles_id_seq', 1, false);


--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 209
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: template-app
--

SELECT pg_catalog.setval('public.users_id_seq', 1, true);


--
-- TOC entry 3000 (class 0 OID 0)
-- Dependencies: 211
-- Name: users_roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: template-app
--

SELECT pg_catalog.setval('public.users_roles_id_seq', 3, true);


--
-- TOC entry 2832 (class 2606 OID 16749)
-- Name: session d_session_pkey; Type: CONSTRAINT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.session
    ADD CONSTRAINT d_session_pkey PRIMARY KEY (session_id);


--
-- TOC entry 2836 (class 2606 OID 16751)
-- Name: setting d_setting_pkey; Type: CONSTRAINT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.setting
    ADD CONSTRAINT d_setting_pkey PRIMARY KEY (setting_id);


--
-- TOC entry 2838 (class 2606 OID 16753)
-- Name: roles roles_name_key; Type: CONSTRAINT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_name_key UNIQUE (name);


--
-- TOC entry 2840 (class 2606 OID 16755)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2834 (class 2606 OID 16757)
-- Name: session token_uni; Type: CONSTRAINT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.session
    ADD CONSTRAINT token_uni UNIQUE (token);


--
-- TOC entry 2842 (class 2606 OID 16759)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2846 (class 2606 OID 16761)
-- Name: users_roles users_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2844 (class 2606 OID 16763)
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- TOC entry 2847 (class 2606 OID 16764)
-- Name: users_roles users_roles_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);


--
-- TOC entry 2848 (class 2606 OID 16769)
-- Name: users_roles users_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: template-app
--

ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- TOC entry 2990 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2024-10-30 09:05:27

--
-- PostgreSQL database dump complete
--

