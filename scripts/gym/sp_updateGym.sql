USE gym_membership;

DROP PROCEDURE IF EXISTS update_gym;

DELIMITER $$
CREATE PROCEDURE update_gym( id BIGINT, `name` VARCHAR(255), address VARCHAR(255), phone VARCHAR(9), email VARCHAR(255), city_id BIGINT)
BEGIN
	UPDATE GYM SET `name` = `name`, address = address, phone = phone, email = email, city_id = city_id WHERE gym_id = id;
END $$
DELIMITER ;
