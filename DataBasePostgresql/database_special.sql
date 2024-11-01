PGDMP  8        	        	    |            template-app %   12.20 (Ubuntu 12.20-0ubuntu0.20.04.1)    16.4 .    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16707    template-app    DATABASE     v   CREATE DATABASE "template-app" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'C.UTF-8';
    DROP DATABASE "template-app";
                template-app    false                        2615    2200    public    SCHEMA     2   -- *not* creating schema, since initdb creates it
 2   -- *not* dropping schema, since initdb creates it
                postgres    false            �           0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    6            �            1259    16708    session    TABLE     ?  CREATE TABLE public.session (
    session_id integer NOT NULL,
    token character varying(50) NOT NULL,
    "user" character varying(100) NOT NULL,
    "isNotDelete" boolean DEFAULT false NOT NULL,
    date_update timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    remote_ip character varying(100)
);
    DROP TABLE public.session;
       public         heap    template-app    false    6            �            1259    16713    d_session_session_id_seq    SEQUENCE     �   CREATE SEQUENCE public.d_session_session_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.d_session_session_id_seq;
       public          template-app    false    202    6            �           0    0    d_session_session_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.d_session_session_id_seq OWNED BY public.session.session_id;
          public          template-app    false    203            �            1259    16715    setting    TABLE     �   CREATE TABLE public.setting (
    setting_id integer NOT NULL,
    name character varying(100) NOT NULL,
    value character varying(100),
    description character varying(200)
);
    DROP TABLE public.setting;
       public         heap    template-app    false    6            �            1259    16718    d_setting_setting_id_seq    SEQUENCE     �   CREATE SEQUENCE public.d_setting_setting_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.d_setting_setting_id_seq;
       public          template-app    false    204    6            �           0    0    d_setting_setting_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.d_setting_setting_id_seq OWNED BY public.setting.setting_id;
          public          template-app    false    205            �            1259    16720    roles    TABLE     a   CREATE TABLE public.roles (
    id integer NOT NULL,
    name character varying(100) NOT NULL
);
    DROP TABLE public.roles;
       public         heap    template-app    false    6            �            1259    16723    roles_id_seq    SEQUENCE     �   CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          template-app    false    6    206            �           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          template-app    false    207            �            1259    16725    users    TABLE     !  CREATE TABLE public.users (
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
    DROP TABLE public.users;
       public         heap    template-app    false    6            �            1259    16736    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          template-app    false    6    208            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          template-app    false    209            �            1259    16738    users_roles    TABLE     w   CREATE TABLE public.users_roles (
    id integer NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL
);
    DROP TABLE public.users_roles;
       public         heap    template-app    false    6            �            1259    16741    users_roles_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.users_roles_id_seq;
       public          template-app    false    210    6            �           0    0    users_roles_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.users_roles_id_seq OWNED BY public.users_roles.id;
          public          template-app    false    211                       2604    16743    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          template-app    false    207    206                       2604    16744    session session_id    DEFAULT     z   ALTER TABLE ONLY public.session ALTER COLUMN session_id SET DEFAULT nextval('public.d_session_session_id_seq'::regclass);
 A   ALTER TABLE public.session ALTER COLUMN session_id DROP DEFAULT;
       public          template-app    false    203    202                       2604    16745    setting setting_id    DEFAULT     z   ALTER TABLE ONLY public.setting ALTER COLUMN setting_id SET DEFAULT nextval('public.d_setting_setting_id_seq'::regclass);
 A   ALTER TABLE public.setting ALTER COLUMN setting_id DROP DEFAULT;
       public          template-app    false    205    204                       2604    16746    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          template-app    false    209    208                       2604    16747    users_roles id    DEFAULT     p   ALTER TABLE ONLY public.users_roles ALTER COLUMN id SET DEFAULT nextval('public.users_roles_id_seq'::regclass);
 =   ALTER TABLE public.users_roles ALTER COLUMN id DROP DEFAULT;
       public          template-app    false    211    210            �          0    16720    roles 
   TABLE DATA           )   COPY public.roles (id, name) FROM stdin;
    public          template-app    false    206   &4       �          0    16708    session 
   TABLE DATA           c   COPY public.session (session_id, token, "user", "isNotDelete", date_update, remote_ip) FROM stdin;
    public          template-app    false    202   c4       �          0    16715    setting 
   TABLE DATA           G   COPY public.setting (setting_id, name, value, description) FROM stdin;
    public          template-app    false    204   �4       �          0    16725    users 
   TABLE DATA           z   COPY public.users (id, username, password, enabled, date, comments, email, first_name, last_name, last_login) FROM stdin;
    public          template-app    false    208   5       �          0    16738    users_roles 
   TABLE DATA           ;   COPY public.users_roles (id, user_id, role_id) FROM stdin;
    public          template-app    false    210   �5       �           0    0    d_session_session_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.d_session_session_id_seq', 18, true);
          public          template-app    false    203            �           0    0    d_setting_setting_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.d_setting_setting_id_seq', 1, true);
          public          template-app    false    205            �           0    0    roles_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.roles_id_seq', 1, false);
          public          template-app    false    207            �           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 1, true);
          public          template-app    false    209            �           0    0    users_roles_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.users_roles_id_seq', 3, true);
          public          template-app    false    211                       2606    16749    session d_session_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.session
    ADD CONSTRAINT d_session_pkey PRIMARY KEY (session_id);
 @   ALTER TABLE ONLY public.session DROP CONSTRAINT d_session_pkey;
       public            template-app    false    202                       2606    16751    setting d_setting_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.setting
    ADD CONSTRAINT d_setting_pkey PRIMARY KEY (setting_id);
 @   ALTER TABLE ONLY public.setting DROP CONSTRAINT d_setting_pkey;
       public            template-app    false    204                       2606    16753    roles roles_name_key 
   CONSTRAINT     O   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_name_key UNIQUE (name);
 >   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_name_key;
       public            template-app    false    206                       2606    16755    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            template-app    false    206                       2606    16757    session token_uni 
   CONSTRAINT     M   ALTER TABLE ONLY public.session
    ADD CONSTRAINT token_uni UNIQUE (token);
 ;   ALTER TABLE ONLY public.session DROP CONSTRAINT token_uni;
       public            template-app    false    202                       2606    16759    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            template-app    false    208                       2606    16761    users_roles users_roles_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.users_roles DROP CONSTRAINT users_roles_pkey;
       public            template-app    false    210                       2606    16763    users users_username_key 
   CONSTRAINT     W   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);
 B   ALTER TABLE ONLY public.users DROP CONSTRAINT users_username_key;
       public            template-app    false    208                       2606    16764 $   users_roles users_roles_role_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES public.roles(id);
 N   ALTER TABLE ONLY public.users_roles DROP CONSTRAINT users_roles_role_id_fkey;
       public          template-app    false    210    206    2840                        2606    16769 $   users_roles users_roles_user_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.users_roles
    ADD CONSTRAINT users_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.users_roles DROP CONSTRAINT users_roles_user_id_fkey;
       public          template-app    false    208    210    2842            �   -   x�3���q�wt����2�p\\�\}�\���!��@v� �y      �      x������ � �      �   �   x�=�M
�0���)�cki�*�����R�^A�E�+���S�������N綽t�	{v�g_r���\8pP,���U5o�*�'V�2 c!��B���7aAV���;a�G��D¤� QF�Z�ߨ�k���6l���*H�      �   �   x���M�@ ���+������DE2B3]J(R�]��\̸�y�'Qr�
�B�rP�28�n�N�A9JO�s����u]Y}��BA��
����X�j��M�D` g��=]����!� ��������'������vF�(��]����ѼA���+�)����Ҿ���TQ�7ӆ>�      �      x�3�4�4�2�F\ƜF��\1z\\\ !��     