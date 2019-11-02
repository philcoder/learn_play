# --- First database schema

# --- !Ups

set ignorecase true;

CREATE TABLE public."user"
(
    id serial,
    name character varying(150) NOT NULL,
    email character varying(150) NOT NULL,
    pw character varying(30) NOT NULL,
    role integer NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT email_uniq UNIQUE (email)

);

ALTER TABLE public."User"
    OWNER to root;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists public."User";

SET REFERENTIAL_INTEGRITY TRUE;