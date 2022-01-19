create table t_group
(
    id     bigint       not null
        primary key,
    name   varchar(256) not null,
    `desc` text         null,
    constraint t_group_id_uindex
        unique (id)
);

create table t_item
(
    sku      bigint            not null
        primary key,
    name     varchar(256)      not null,
    `desc`   varchar(512)      null,
    category int    default 0  null,
    tags     text              null,
    count    int    default 0  null,
    status   int    default 0  null,
    group_id bigint default -1 null
);


