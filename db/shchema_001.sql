create table persons
(
    id       serial primary key,
    login    varchar(255) not null unique,
    password varchar(255) not null ,
    role_id int references roles (id)
);

create table roles
(
    id   serial primary key,
    role varchar(255) unique
);

create table rooms
(
    id        serial primary key,
    name      varchar(255) unique,
    person_id int references persons (id)
);

create table messages
(
    id        serial primary key,
    text      varchar(1000),
    person_id int references persons (id),
    room_id int references rooms(id)
);

insert into roles(role) values ('role_admin');
insert into roles(role) values ('role_user');

insert into persons(login, password, role_id) values ('admin', '123321', (select id from roles where role = 'role_admin'));
insert into persons(login, password, role_id) values ('user', '123321', (select id from roles where role = 'role_user'));

insert into rooms(name, person_id) values ('общение', 2);
insert into rooms(name, person_id) values ('еда', 1);

insert into messages(text, person_id, room_id) values ('сообщение', 4, 3);
insert into messages(text, person_id, room_id) values ('общение', 5, 4);