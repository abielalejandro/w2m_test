create sequence superheroes_seq;
create table superheroes (
        id bigint not null,
        name varchar(255),
        power varchar(255),
        real_name varchar(255),
        primary key (id),
        CONSTRAINT UC_superheroes_Name UNIQUE (name)
)