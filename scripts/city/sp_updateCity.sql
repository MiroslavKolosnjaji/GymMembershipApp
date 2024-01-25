USE gym_membership;

DROP PROCEDURE IF EXISTS update_city;

DELIMITER $$
CREATE PROCEDURE update_city(id BIGINT, `name` VARCHAR(50), zip_code VARCHAR(50))
BEGIN
	UPDATE CITY SET `name` = `name`, zip_code = zip_code WHERE city_id = id;
END $$
DELIMITER ;