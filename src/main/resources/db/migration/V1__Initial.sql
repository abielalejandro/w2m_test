create sequence superheroes_seq;
create table superheroes (
        id bigint not null,
        created_at timestamp(6),
        name varchar(255),
        power varchar(255),
        real_name varchar(255),
        primary key (id)
)