USE gym_membership;

DROP PROCEDURE IF EXISTS insert_user;

DELIMITER $$
CREATE PROCEDURE insert_user(first_name VARCHAR(50), last_name VARCHAR(50), phone VARCHAR(9), email VARCHAR(255), `password` VARCHAR(50), city_id BIGINT)
BEGIN
	INSERT INTO `USER`(first_name, last_name, phone, email, `password`, city_id) VALUES(first_name, last_name, phone, email, `password`, city_id);
END $$
DELIMITER ;
