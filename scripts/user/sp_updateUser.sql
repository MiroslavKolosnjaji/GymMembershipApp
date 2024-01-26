USE gym_membership;

DROP PROCEDURE IF EXISTS update_user;

DELIMITER $$
CREATE PROCEDURE update_user(id BIGINT, first_name VARCHAR(50), last_name VARCHAR(50), phone VARCHAR(9), email VARCHAR(255), `password` VARCHAR(50), city_id BIGINT)
BEGIN
	UPDATE `USER` SET first_name = first_name, last_name = last_name, phone = phone, email = email, `password` = `password` WHERE user_id = id;
END $$
DELIMITER ;