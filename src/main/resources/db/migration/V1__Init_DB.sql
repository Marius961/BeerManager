create table cart_items
(
    id              bigint  not null,
    quantity        integer not null,
    is_add_to_order bit     not null,
    user_id         bigint,
    product_id      bigint  not null,
    primary key (id)
) engine = InnoDB;

create table category
(
    id         bigint not null,
    image_name varchar(255),
    name       varchar(20),
    primary key (id)
) engine = InnoDB;

create table hibernate_sequence
(
    next_val bigint
) engine = InnoDB;

insert into hibernate_sequence
values (14);
insert into hibernate_sequence
values (14);
insert into hibernate_sequence
values (14);
insert into hibernate_sequence
values (14);
insert into hibernate_sequence
values (14);
insert into hibernate_sequence
values (14);
insert into hibernate_sequence
values (14);
insert into hibernate_sequence
values (14);
insert into hibernate_sequence
values (14);
insert into hibernate_sequence
values (14);
insert into hibernate_sequence
values (14);
insert into hibernate_sequence
values (14);
insert into hibernate_sequence
values (14);



create table measurement_unit
(
    id         bigint not null,
    full_name  varchar(20),
    short_name varchar(16),
    primary key (id)
) engine = InnoDB;
create table order_addresses
(
    id                      bigint not null,
    building_number         varchar(10),
    city                    varchar(36),
    office_number           varchar(10),
    post_code               varchar(6),
    recipient_full_name     varchar(36),
    recipient_mobile_number varchar(10),
    region                  varchar(36),
    street                  varchar(36),
    order_id                bigint,
    primary key (id)
) engine = InnoDB;
create table order_stats
(
    id              bigint not null,
    status_comment  varchar(255),
    status_set_date datetime,
    order_id        bigint not null,
    status_id       bigint not null,
    primary key (id)
) engine = InnoDB;
create table ordered_item
(
    id          bigint           not null,
    quantity    integer          not null,
    total_price double precision not null,
    product_id  bigint           not null,
    order_id    bigint,
    primary key (id)
) engine = InnoDB;
create table orders
(
    id            bigint           not null,
    comment       varchar(512),
    creation_date datetime,
    is_canceled   bit              not null,
    total_price   double precision not null,
    user_id       bigint,
    seller_id     bigint,
    primary key (id)
) engine = InnoDB;
create table product
(
    id                         bigint           not null,
    active                     bit              not null,
    description                varchar(512),
    image_name                 varchar(255),
    name                       varchar(64),
    orders_count               bigint           not null,
    price_for_measurement_unit double precision not null,
    measurement_unit_id        bigint           not null,
    seller_id                  bigint,
    subcategory_id             bigint           not null,
    primary key (id)
) engine = InnoDB;
create table seller
(
    id      bigint not null,
    name    varchar(255),
    user_id bigint,
    primary key (id)
) engine = InnoDB;
create table shipping_address
(
    id                      bigint not null,
    building_number         varchar(10),
    city                    varchar(36),
    office_number           varchar(10),
    post_code               varchar(6),
    recipient_full_name     varchar(36),
    recipient_mobile_number varchar(10),
    region                  varchar(36),
    street                  varchar(36),
    user_id                 bigint,
    primary key (id)
) engine = InnoDB;
create table stats
(
    id          bigint not null,
    code        varchar(255),
    description varchar(255),
    name        varchar(255),
    primary key (id)
) engine = InnoDB;
create table subcategory
(
    id          bigint not null,
    name        varchar(32),
    category_id bigint,
    primary key (id)
) engine = InnoDB;
create table user
(
    id         bigint not null,
    active     INTEGER,
    email      varchar(32),
    first_name varchar(64),
    password   varchar(512),
    username   varchar(16),
    primary key (id)
) engine = InnoDB;
create table user_role
(
    user_id bigint not null,
    roles   varchar(255)
) engine = InnoDB;
alter table category
    add constraint UK_46ccwnsi9409t36lurvtyljak unique (name);
alter table user
    add constraint UK_ob8kqyqqgmefl0aco34akdtpe unique (email);
alter table user
    add constraint UK_sb8bbouer5wak8vyiiy4pf2bx unique (username);
alter table cart_items
    add constraint FKl7je3auqyq1raj52qmwrgih8x foreign key (product_id) references product (id);
alter table cart_items
    add constraint FKkjv4yjjdlt4hd9ayey6mti09m foreign key (user_id) references user (id);
alter table order_addresses
    add constraint FK50ktlegmhrk0yuibum1sg0jb8 foreign key (order_id) references orders (id);
alter table order_stats
    add constraint FKf9p28quy5lthhg5kt0lrdpb4s foreign key (order_id) references orders (id);
alter table order_stats
    add constraint FKt2bqfhncdvn3qxuunkj0vgm9v foreign key (status_id) references stats (id);
alter table ordered_item
    add constraint FKaq0vwxoytp1o7acx7bljwr4u foreign key (product_id) references product (id);
alter table ordered_item
    add constraint FK51dj38kcdahtjcx8h2be767sr foreign key (order_id) references orders (id);
alter table orders
    add constraint FKiqoyghlcoagihjeufolwetin7 foreign key (seller_id) references seller (id);
alter table orders
    add constraint FKel9kyl84ego2otj2accfd8mr7 foreign key (user_id) references user (id);
alter table product
    add constraint FKcepyohgn8acby3uhc81ojy4i foreign key (measurement_unit_id) references measurement_unit (id);
alter table product
    add constraint FKesd6fy52tk7esoo2gcls4lfe3 foreign key (seller_id) references seller (id);
alter table product
    add constraint FKku369nri8u3s17uom8or57trs foreign key (subcategory_id) references subcategory (id);
alter table seller
    add constraint FK6rgw0e6tb24n93c27njlv0wcl foreign key (user_id) references user (id);
alter table shipping_address
    add constraint FKqijab83dlbj00gytfswvh7ri9 foreign key (user_id) references user (id);
alter table subcategory
    add constraint FKe4hdbsmrx9bs9gpj1fh4mg0ku foreign key (category_id) references category (id);
alter table user_role
    add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id);