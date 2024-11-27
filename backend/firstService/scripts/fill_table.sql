insert into dragon
(id, age, color, coordinate_x, coordinate_y, creation_date, eyes_count, name, speaking, wingspan)
values
(1, 1000, 'blue', 0, 0, now(), 2, 'John Doe', true, 10000),
(2, 1000, 'brown', 0, 0, now(), 2, 'Ivan', true, 10000);

insert into dragon
(id, age, color, coordinate_x, coordinate_y, creation_date, eyes_count, name, speaking, wingspan)
values
(3, 1000, 'blue', 0, 0, now(), 2, 'James', true, 10000),
(4, 1000, 'blue', 0, 0, now(), 2, 'Petr', true, 10000),
(5, 1000, 'brown', 0, 0, now(), 2, 'Sidr', true, 10000),
(6, 1000, 'blue', 0, 0, now(), 2, 'Smaug', true, 10000),
(7, 1000, 'brown', 0, 0, now(), 2, 'Harrison', true, 10000),
(8, 1000, 'brown', 0, 0, now(), 2, 'Zach', true, 10000),
(9, 1000, 'blue', 0, 0, now(), 2, 'Sigma', true, 10000),
(10, 1000, 'brown', 0, 0, now(), 2, 'Beta', true, 10000);

insert into team (id, name, power) values
(1, 'Team 1', 100),
(2, 'Team 2', 200);

insert into hunter (id, first_name, last_name, strength, team_id) values
(1, 'John', 'Doe', 100, 1),
(2, 'Ivan', 'Ivanov', 200, 2);

update dragon
set color = 'brown'
where id = 2;
