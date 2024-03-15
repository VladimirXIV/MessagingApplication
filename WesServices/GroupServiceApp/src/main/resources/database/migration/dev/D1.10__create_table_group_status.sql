create table group_status (

    code bigint    not null,
    name char(200) not null,

    constraint group_status_pk primary key (code),
    constraint group_status_name_uniqueness unique (name)
);