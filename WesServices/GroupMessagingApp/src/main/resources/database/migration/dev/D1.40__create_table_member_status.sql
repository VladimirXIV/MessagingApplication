create table member_status (

    code bigint    not null,
    name char(200) not null,

    constraint member_status_pk primary key (code)
);