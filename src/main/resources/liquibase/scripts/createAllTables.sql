-- liquebase formatted sql
-- changeset abdullinru:1

create table computers
(
    id              Serial      primary key,
    ser_nomer        integer,
    manufacturer    varchar,
    price           float,
    quantity        integer,
    type            varchar
);
create table hdd
(
    id              Serial      primary key,
    ser_nomer        integer     not null,
    manufacturer    varchar     not null,
    price           float     not null,
    quantity        integer     not null,
    storage         integer     not null
);
create table monitors
(
    id              Serial      primary key,
    ser_nomer        integer     not null,
    manufacturer    varchar     not null,
    price           float     not null,
    quantity        integer     not null,
    diagonal        integer     not null
);
create table notebooks
(
    id              Serial      primary key,
    ser_nomer        integer     not null,
    manufacturer    varchar     not null,
    price           float     not null,
    quantity        integer     not null,
    size            varchar     not null
);
