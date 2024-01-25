USE gym_membership;

DROP PROCEDURE IF EXISTS get_all_cities;

DELIMITER $$
CREATE PROCEDURE get_all_cities()
BEGIN
	SELECT city_id, `name`, zip_code FROM CITY ORDER BY `name` DESC;
END $$
DELIMITER ;