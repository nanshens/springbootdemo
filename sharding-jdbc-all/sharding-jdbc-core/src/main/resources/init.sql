
CREATE TABLE if not exists public.t_config();
-----------
CREATE TABLE if not exists public.customer
(
        id int8,
    name varchar(255),
    code varchar(255),
    phone varchar(255),
    PRIMARY KEY(id)
);

CREATE TABLE if not exists public.sales_order_0
(
        id int8,
    name varchar(255),
    code varchar(255),
    customer_id int8,
    PRIMARY KEY(id)
);
alter table sales_order_0 add constraint FK_customer foreign key (customer_id) references customer(id);

CREATE TABLE if not exists public.item
(
        id int8,
    name varchar(255),
    unit varchar(255),
    PRIMARY KEY(id)
);

CREATE TABLE if not exists public.sales_order_line_0
(
        id int8,
    sales_order_id int8,
    item_id int8,
    quantity numeric(19,4),
    price numeric(19,4),
    cost numeric(19,4),
    PRIMARY KEY(id)
);
alter table sales_order_line_0 add constraint FK_sales_order_0 foreign key (sales_order_id) references sales_order_0(id);
alter table sales_order_line_0 add constraint FK_item foreign key (item_id) references item(id);

-----------

CREATE TABLE if not exists public.sales_order_1
(
        id int8,
    name varchar(255),
    code varchar(255),
    customer_id int8,
    PRIMARY KEY(id)
);
alter table sales_order_1 add constraint FK_customer foreign key (customer_id) references customer(id);

CREATE TABLE if not exists public.sales_order_line_1
(
        id int8,
    sales_order_id int8,
    item_id int8,
    quantity numeric(19,4),
    price numeric(19,4),
    cost numeric(19,4),
    PRIMARY KEY(id)
);
alter table sales_order_line_1 add constraint FK_sales_order_1 foreign key (sales_order_id) references sales_order_1(id);
alter table sales_order_line_1 add constraint FK_item foreign key (item_id) references item(id);

-----------

CREATE TABLE if not exists public.sales_order_2
(
        id int8,
    name varchar(255),
    code varchar(255),
    customer_id int8,
    PRIMARY KEY(id)
);
alter table sales_order_2 add constraint FK_customer foreign key (customer_id) references customer(id);

CREATE TABLE if not exists public.sales_order_line_2
(
        id int8,
    sales_order_id int8,
    item_id int8,
    quantity numeric(19,4),
    price numeric(19,4),
    cost numeric(19,4),
    PRIMARY KEY(id)
);
alter table sales_order_line_2 add constraint FK_sales_order_2 foreign key (sales_order_id) references sales_order_2(id);
alter table sales_order_line_2 add constraint FK_item foreign key (item_id) references item(id);