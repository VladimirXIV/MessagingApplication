create table group_type (

    id   bigint    not null,
    name char(200) not null,

    constraint group_type_pk primary key (id),
    constraint group_type_name_uniqueness unique (name)
);