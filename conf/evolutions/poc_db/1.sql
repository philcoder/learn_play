--- !Ups

CREATE TABLE if not exists public."user"
(
    id serial,
    name character varying(150),
    email character varying(150) NOT NULL,
    pw character varying(30) NOT NULL,
    role integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT email_uniq UNIQUE (email)
);

ALTER TABLE public."user"
    OWNER to root;

--- !Downs

-- drop table if exists public."user";
