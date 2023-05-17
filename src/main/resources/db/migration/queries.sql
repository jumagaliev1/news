SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = ON;
SET check_function_bodies = FALSE;
SET client_min_messages = WARNING;
SET search_path = public, extensions;
SET default_tablespace = '';
SET default_with_oids = FALSE;


CREATE TABLE users
(
    id         bigint PRIMARY KEY,
    created_at timestamp,
    updated_at timestamp,
    email      varchar(64) NOT NULL
        CONSTRAINT uk_email UNIQUE,
    password   varchar(60) NOT NULL,
    username   varchar(32) NOT NULL
        CONSTRAINT uk_username UNIQUE
);

CREATE TABLE roles
(
    id bigint PRIMARY KEY,
    name varchar(32) not null
);

CREATE TABLE news_topics
(
    id bigint primary key,
    name varchar(32) not null
);

CREATE TABLE news_source
(
    id bigint primary key,
    name varchar(32) not null
);

CREATE TABLE news
(
    id bigint primary key,
    title varchar(64) not null,
    content text not null,
    foreign key (id) references news_source (id),
    foreign key (id) references news_topics (id),
    created_at timestamp,
    updated_at timestamp,
    author_id bigint
);