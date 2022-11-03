create table "user"
(
    id            bigint not null,
    first_name    varchar(500),
    middle_name   varchar(500),
    last_name     varchar(500),
    username      varchar(500),
    mobile        varchar(500),
    email         varchar(500),
    registered_at timestamp,
    last_login    timestamp,
    intro         text,
    profile       text,
    new_column    integer,

    constraint user_pk primary key (id)
);

alter table "user" owner to user_dev_1;