USE gym_membership;

DROP PROCEDURE IF EXISTS insert_gym;

DELIMITER $$
CREATE PROCEDURE insert_gym(`name` VARCHAR(255), address VARCHAR(255), phone VARCHAR(9), email VARCHAR(255), city_id BIGINT)
BEGIN
	INSERT INTO GYM (`name`, address, phone, email, city_id) VALUES(`name`, address, phone, email, city_id);
END $$
DELIMITER ;
