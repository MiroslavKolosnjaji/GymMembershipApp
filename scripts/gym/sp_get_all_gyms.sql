USE gym_membership;

DROP PROCEDURE IF EXISTS get_add_gyms;

DELIMITER $$
CREATE PROCEDURE get_all_gyms()
BEGIN
	SELECT gym_id, `name`, address, phone, email, city_id FROM GYM;
END $$
DELIMITER ;
