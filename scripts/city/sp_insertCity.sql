USE gym_membership; 

DROP PROCEDURE IF EXISTS insert_city;

DELIMITER $$
CREATE PROCEDURE insert_city (`name` VARCHAR(50), zip_code VARCHAR(5))
BEGIN
	INSERT INTO CITY(`name`, zip_code) VALUES(`name`, zip_code);
END $$
DELIMITER ;    