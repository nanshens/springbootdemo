create extension "uuid-ossp";
CREATE TABLE if not exists public.customer
(
    id varchar(255),
    name varchar(255),
    code varchar(255),
    phone varchar(255),
    PRIMARY KEY(id)
);

CREATE TABLE if not exists public.sales_order
(
    id varchar(255),
    name varchar(255),
    code varchar(255),
--     customer_id varchar(255),
    customer_id varchar(255)
        constraint fkqqe3xj99rblvm5n0h0cp48gsa
        references customer,
    PRIMARY KEY(id)
);