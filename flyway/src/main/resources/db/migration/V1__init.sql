create table if not exists public.customer
(
    id character varying(40) NOT NULL,
    active character(1),
    name character varying(40)
)
WITH (
    OIDS = FALSE
);