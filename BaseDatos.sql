--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

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
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    password integer NOT NULL,
    status boolean NOT NULL,
    client_id bigint NOT NULL
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- Name: persons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persons (
    age integer NOT NULL,
    person_id bigint NOT NULL,
    identification character varying(10) NOT NULL,
    phone character varying(10) NOT NULL,
    gender character varying(30) NOT NULL,
    address character varying(45) NOT NULL,
    name character varying(50) NOT NULL,
    CONSTRAINT persons_gender_check CHECK (((gender)::text = ANY ((ARRAY['MALE'::character varying, 'FEMALE'::character varying, 'OTHER'::character varying])::text[])))
);


ALTER TABLE public.persons OWNER TO postgres;

--
-- Name: persons_person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.persons ALTER COLUMN person_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.persons_person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (client_id);


--
-- Name: persons persons_identification_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_identification_key UNIQUE (identification);


--
-- Name: persons persons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (person_id);


--
-- Name: clients fkpnhrp6ctlsaxqyge4owep2fqs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT fkpnhrp6ctlsaxqyge4owep2fqs FOREIGN KEY (client_id) REFERENCES public.persons(person_id);


--
-- Name: accounts; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.accounts (
    initial_balance numeric(38,2) NOT NULL,
    status boolean NOT NULL,
    account_id bigint NOT NULL,
    client_id bigint NOT NULL,
    account_type character varying(10) NOT NULL,
    number character varying(20) NOT NULL,
    CONSTRAINT accounts_account_type_check CHECK (((account_type)::text = ANY ((ARRAY['SAVINGS'::character varying, 'CHECKING'::character varying])::text[])))
);


ALTER TABLE public.accounts OWNER TO postgres;

--
-- Name: accounts_account_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.accounts ALTER COLUMN account_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.accounts_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: movements; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.movements (
    balance numeric(38,2) NOT NULL,
    date date NOT NULL,
    value numeric(38,2) NOT NULL,
    account_id bigint,
    movement_id bigint NOT NULL,
    movement_type character varying(10) NOT NULL,
    CONSTRAINT movements_movement_type_check CHECK (((movement_type)::text = ANY ((ARRAY['WITHDRAWAL'::character varying, 'DEPOSIT'::character varying])::text[])))
);


ALTER TABLE public.movements OWNER TO postgres;

--
-- Name: movements_movement_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.movements ALTER COLUMN movement_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.movements_movement_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: accounts accounts_number_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_number_key UNIQUE (number);


--
-- Name: accounts accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (account_id);


--
-- Name: movements movements_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movements
    ADD CONSTRAINT movements_pkey PRIMARY KEY (movement_id);


--
-- Name: movements fk1a6nru7corjv5b2vidld4ef5r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.movements
    ADD CONSTRAINT fk1a6nru7corjv5b2vidld4ef5r FOREIGN KEY (account_id) REFERENCES public.accounts(account_id);
	
--
-- PostgreSQL database dump complete
--
