INSERT INTO CITY(`name`,zip_code) VALUES("Novi Sad", 21000), ("Beograd", 11000), ("Subotica", 24100);
select * from city ORDER BY city_id;

INSERT INTO MEMBER(first_name, last_name, phone, email) VALUES("Miroslav", "Kolosnjaji", "063956847", "midzi91@hotmail.com");
SELECT * FROM MEMBER;

INSERT INTO ROLE(role) VALUES("ADMIN"), ("MODERATOR"), ("USER");
SELECT * FROM ROLE;

INSERT INTO GYM(`name`, address, phone, email, city_id) VALUES("Venom", "Bulevar Evrope 233", "066324511", "venom@gmail.com", 1);
SELECT * FROM gym;

INSERT INTO USER(first_name, last_name, phone, email, password, city_id) VALUES("Janko", "Jankovic", "064284234", "janko@hotmail.com", "jankela2", 2);
select * from user;

insert into user_role values(1,1);
insert into membership values(1,1, "2024-01-24", "2024-02-24");
select * FROM membership;


SELECT G.name gym, CONCAT(M.first_name, " ", M.last_name) `member`, date_from, date_to FROM membership MSH JOIN gym G USING(gym_id) JOIN `member` M USING(member_id);
SELECT current_date;

CALL insert_city("Kragujevac", "55060");
SELECT * FROM CITY;

CALL update_city(7, "Kragujevac", "55070");
CALL delete_city(7);

CALL get_all_cities();
CALL insert_member("Jovan", "Jovic", "066432556", "jovic@example.com");
CALL update_member(2, "Jovan", "Jovic", "066432536", "jovic@example.com");
CALL delete_member(3);
CALL get_all_members();
SELECT * FROM MEMBER;

CALL insert_role("X");
CALL update_role(4, "Y");
CALL delete_role(4);
CALL get_all_roles();
SELECT * FROM role;
